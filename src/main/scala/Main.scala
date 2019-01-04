import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable

import scala.util.Success

//import scala.collection.generic.BitOperations.Int
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Main extends App {

  val db = Database.forConfig("mysqlDB")
  val peopleTable = TableQuery[People]

  val dropPeopleCmd = DBIO.seq(peopleTable.schema.drop)
  val initPeopleCmd = DBIO.seq(peopleTable.schema.create)

  def dropDB = {
    db.run(dropPeopleCmd)
  }

  def initialisePeople = {
    db.run(initPeopleCmd)
  }

  def runQuery = {
    val query = peopleTable ++= Seq(
      (10, "Jack", "Wood", 36),
      (20, "Tim", "Brown", 24)
    )
    db.run(query)
  }

  def listPeople = {
    db.run(peopleTable.result).map(_.foreach {
      case (id, fName, lName, age) => println(s" $id $fName $lName $age")})
  }

  def closeDbConnection(): Unit = {
    db.close()
  }

//  db.run(sql"""show tables""".as[String]).onComplete({
//    case scala.util.Success(value) => value.foreach(println)
//  })

  def getCommonName(): Unit = {
    db.run(peopleTable.result).onComplete{
      case Success(value) =>
        value.map(value => Tuple2(value._2, value._3)).groupBy(_._1).mapValues(_.size)
    }
  }


  val operations = dropDB flatMap(res =>initialisePeople) flatMap(res=>runQuery) flatMap(res=>listPeople)
  operations.onComplete{case scala.util.Success(value) => println("Done with operations")}


  Thread.sleep(10000)

}