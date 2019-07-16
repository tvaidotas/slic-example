import slick.jdbc.MySQLProfile.api._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object Main extends App {

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
    //Attempt to drop the table, Await does not block here
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
      case Success(_) => runQuery
      case Failure(error) => println("Initialising the table failed due to: " + error.getMessage)
    }
  }

  def runQuery = {
    val insertPeople = Future {
      val query = peopleTable ++= Seq(
        Person(10, "Jack", "Wood", 36),
        Person(20, "Tim", "Brown", 24)
      )
    // insert into `PEOPLE` (`PER_FNAME`,`PER_LNAME`,`PER_AGE`)  values (?,?,?)
    println(query.statements.head) // would print out the query one line up
    db.run(query)
    }
    Await.result(insertPeople, Duration.Inf).andThen {
      case Success(_) => updateAge()
      case Failure(error) => println("Welp! Something went wrong! " + error.getMessage)
    }
  }

  def updateAge() = {
    val query = Future {
      val ageSelected = for {people <- peopleTable if people.age === 36} yield people.age
      //(for {people <- peopleTable if people.age === 36} yield people.age).update(46)
      val ageUpdated = ageSelected.update(46)
      db.run(
        (for {people <- peopleTable if people.age === 36} yield people.age).update(46)
      )
    }
    Await.result(query, Duration.Inf).andThen {
      case Success(_) =>  listPeople  //cleanup DB connection
      case Failure(error) => println("Updating people failed due to: " + error.getMessage)
    }
  }

  def listPeople = {
    val queryFuture = Future {
      // simple query that selects everything from People and prints them out
      db.run(peopleTable.result).map(_.foreach {
        case person: Person => println(s" ${person.id} ${person.fName} ${person.lName} ${person.age}")})
    }
    Await.result(queryFuture, Duration.Inf).andThen {
      case Success(_) =>  db.close()  //cleanup DB connection
      case Failure(error) => println("Listing people failed due to: " + error.getMessage)
    }
  }

  dropDB
  Thread.sleep(10000)
}