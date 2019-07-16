import slick.jdbc.MySQLProfile.api._

case class Person(id: Int, fName: String, lName: String, age: Int)

class People(tag: Tag)
  extends Table[Person](tag, "PEOPLE"){
  def id = column[Int]("PER_ID", O.PrimaryKey, O.AutoInc)
  def fName = column[String]("PER_FNAME")
  def lName = column[String]("PER_LNAME")
  def age = column[Int]("PER_AGE")
  def * = (id, fName, lName, age) <> (Person.tupled, Person.unapply)
}
