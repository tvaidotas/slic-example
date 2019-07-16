package main

import slick.jdbc.MySQLProfile.api._

object People {

  val peopleTable = TableQuery[People]
  val peopleAddressesTable = TableQuery[Addresses]

  case class Person(id: Int, fName: String, lName: String, age: Int)

  class People(tag: Tag)
    extends Table[Person](tag, "PEOPLE") {
    def id = column[Int]("PER_ID", O.PrimaryKey, O.AutoInc)

    def fName = column[String]("PER_FNAME")

    def lName = column[String]("PER_LNAME")

    def age = column[Int]("PER_AGE")

    def * = (id, fName, lName, age) <> (Person.tupled, Person.unapply)
  }

  case class Address(
                      supID:Int,
                      houseNo: Int,
                      street:String,
                      city:String,
                      postCode:String)

  class Addresses(tag: Tag) extends Table[Address](tag, "Addresses"){
    def supID = column[Int]("PER_ID")
    def personId =
      foreignKey("FK", supID, peopleAddressesTable)(
        _.supID,
        onUpdate=ForeignKeyAction.Restrict,
        onDelete=ForeignKeyAction.Cascade)
    def houseNo = column[Int]("HOUSE_No")
    def street = column[String]("STREET")
    def city = column[String]("CITY")
    def postCode = column[String]("POSTCODE")
    def * = (supID,  houseNo, street, city, postCode) <> (Address.tupled, Address.unapply)
  }

}
