import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object Delete{
  def deleteAll(): Unit ={
    // The config string refers to mysqlDB that we defined in application.conf
    val db = Database.forConfig("mysqlDB")
    // represents the actual table on which we will be building queries on
    val peopleTable = TableQuery[People]

    val dropPeopleCmd = DBIO.seq(peopleTable.schema.drop)
    val initPeopleCmd = DBIO.seq(peopleTable.schema.create)

    def dropDB = {
      //do a drop followed by initialisePeople
      val dropFuture = Future{
        db.run(dropPeopleCmd)
      }
      Await.result(dropFuture, Duration.Inf).andThen{
        case Success(_) =>  initialisePeople
        case Failure(error) => println("Dropping the table failed due to: " + error.getMessage)
          initialisePeople
      }
    }
    def initialisePeople = {
      //initialise people
      val setupFuture =  Future {
        db.run(initPeopleCmd)
      }
      //once our DB has finished initializing we are ready to roll, Await does not block
      Await.result(setupFuture, Duration.Inf).andThen{
        case Success(_) => println("DB is Now Blank")
          db.close()
        case Failure(error) => println("Initialising the table failed due to: " + error.getMessage)
      }
    }
    dropDB
    Thread.sleep(1000)
  }


  def deleteOne(fNameInput: String ,lNameInput : String): Unit = {
    val db = Database.forConfig("mysqlDB")
    val peopleTable = TableQuery[People]


    def deleteUser = {
      val dropFuture = Future{
        db.run(peopleTable.filter(_.fName === fNameInput).filter(_.lName === lNameInput).delete)
      }
      //Attempt to drop the table, Await does not block here
      Await.result(dropFuture, Duration.Inf).andThen{
        case Success(_) =>  println(s"Done Removing $fNameInput $lNameInput")
        case Failure(error) => println("Dropping the table failed due to: " + error.getMessage)
      }
    }

    def listPeople = {
      val queryFuture = Future {
        db.run(peopleTable.result).map(_.foreach {
          case (id, fName, lName, age) => println(s" $id $fName $lName $age")})
      }
      Await.result(queryFuture, Duration.Inf).andThen {
        case Success(_) =>  db.close()  //cleanup DB connection
        case Failure(error) => println("Listing people failed due to: " + error.getMessage)
      }
    }
    deleteUser
    Thread.sleep(10000)
  }
}