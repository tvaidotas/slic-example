import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object Create{

  def newPeople(id: Int ,fName: String ,lName: String ,age: Int): Unit ={
    val db = Database.forConfig("mysqlDB")
    val peopleTable = TableQuery[People]

    def runQuery = {
      val insertPeople = Future {
        val query = peopleTable ++= Seq(
          (id, fName, lName, age)
        )
        // insert into `PEOPLE` (`PER_FNAME`,`PER_LNAME`,`PER_AGE`)  values (?,?,?)
        println(query.statements.head) // would print out the query one line up
        db.run(query)
      }
      Await.result(insertPeople, Duration.Inf).andThen {
        case Success(_) =>
          listPeople
          println("WE GOT IT LAD")
        case Failure(error) => println("Welp! Something went wrong! " + error.getMessage)
      }
    }

    def listPeople = {
      val queryFuture = Future {
        // simple query that selects everything from People and prints them out
        db.run(peopleTable.result).map(_.foreach {
          case (id, fName, lName, age) => println(s" $id $fName $lName $age")
        })
      }
      Await.result(queryFuture, Duration.Inf).andThen {
        case Success(_) => db.close() //cleanup DB connection
        case Failure(error) => println("Listing people failed due to: " + error.getMessage)
      }
    }
    runQuery
    Thread.sleep(1000)
  }
}