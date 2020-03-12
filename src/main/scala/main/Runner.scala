package main

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import slick.jdbc.MySQLProfile.api._
import People._
import scala.concurrent.ExecutionContext.Implicits.global


object Runner extends App {

  val db = Database.forConfig("mysqlDB")

  val dropPeopleCmd = DBIO.seq(peopleTable.schema.dropIfExists)
  val dropPeopleAddressesCmd = DBIO.seq(peopleAddressesTable.schema.dropIfExists)
  val initPeopleCmd = DBIO.seq(peopleTable.schema.createIfNotExists)
  val initPeopleAddressesCmd = DBIO.seq(peopleAddressesTable.schema.createIfNotExists)

  def dropDB = {
    val dropFuture = Future{
      db.run(DBIO.seq(dropPeopleCmd, dropPeopleAddressesCmd))
    }
    Await.result(dropFuture, Duration.Inf).andThen{
      case Success(_) =>  initialisePeople
      case Failure(error) => println("Dropping the table failed due to: " + error.getMessage)
        initialisePeople
    }
  }

  def initialisePeople = {
    val setupFuture =  Future {
      db.run(DBIO.seq(initPeopleCmd, initPeopleAddressesCmd))
    }
    Await.result(setupFuture, Duration.Inf).andThen{
      case Success(_) => runQuery
      case Failure(error) => println("Initialising the table failed due to: " + error.getMessage)
    }
  }

  def runQuery = {
    val insertPeople = Future {
      val query = DBIO.seq(
        peopleTable ++= Seq(
          Person(10, "Jack", "Wood", 36),
          Person(20, "Tim", "Brown", 24)
        ),
        peopleAddressesTable ++= Seq(
          Address(1,1,"blah", "street", "m265pl"),
          Address(2,2,"blah", "street", "m265pl"),
        )
      )
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
      db.run(
        (for {people <- peopleTable if people.age === 36} yield people.age).update(46)
      )
    }
    Await.result(query, Duration.Inf).andThen {
      case Success(_) =>  listPeople
      case Failure(error) => println("Updating people failed due to: " + error.getMessage)
    }
  }

  def listPeople = {
    val queryFuture = Future {
      db.run(peopleTable.result).map(_.foreach {
        case person: Person => println(s" ${person.id} ${person.fName} ${person.lName} ${person.age}")})
    }
    Await.result(queryFuture, Duration.Inf).andThen {
      case Success(_) =>  db.close()
      case Failure(error) => println("Listing people failed due to: " + error.getMessage)
    }
  }

  dropDB
  Thread.sleep(10000)
}

