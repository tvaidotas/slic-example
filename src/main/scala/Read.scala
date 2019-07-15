import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object Read {

  def getAll(): Unit= {
  val db = Database.forConfig("mysqlDB")
  val peopleTable = TableQuery[People]

    println("All DB Info")

  def listPeople = {
    val queryFuture = Future {
      db.run(peopleTable.result).map(_.foreach {
        case (id, fName, lName, age) => println(s" $id $fName $lName $age")
      })
    }
    Await.result(queryFuture, Duration.Inf).andThen {
      case Success(_) => db.close() //cleanup DB connection
      case Failure(error) => println("Listing people failed due to: " + error.getMessage)
    }
  }
    listPeople
    Thread.sleep(1000)
  }

  def getOne (idInput: Int ,fNameInput: String ,lNameInput: String ,ageInput: Int , exactCheck: Boolean = true) :Unit = {
    println(s"With The Parameters\n ${if (!exactCheck){"Similar Match"} else "Exact Match"}   \n Name:$fNameInput \n Surname:$lNameInput \n We Found")
  val db = Database.forConfig("mysqlDB")
      val peopleTable = TableQuery[People]

      def listPeople = {
        val queryFuture = Future {
          // simple query that selects everything from People and prints them out
          db.run(peopleTable.result).map(_.foreach {
            case (id, fName, lName, age) =>
              exactCheck match {
                case true => if (fName == fNameInput && lName == lNameInput){println(s" $id $fName $lName $age")}
                case false => if (fName.contains(fNameInput) && lName.contains(lNameInput)){println(s" $id $fName $lName $age")}
              }
          })
        }
        Await.result(queryFuture, Duration.Inf).andThen {
          case Success(_) =>  db.close()  //cleanup DB connection
          case Failure(error) => println("Listing people failed due to: " + error.getMessage)
        }
      }
      listPeople
      Thread.sleep(1000)
  }
}