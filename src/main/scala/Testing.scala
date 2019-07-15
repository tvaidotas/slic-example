

object Testing {
  def main(args: Array[String]): Unit = {
    Create.newPeople(1,"John","Smith",59)
    Delete.deleteAll()
    Create.newPeople(1,"John","Smith",27)
    Read.getAll()
    Read.getOne(10,"John","Smith",34,exactCheck = true)
    Create.newPeople(7,"Joe","Simmons",62)
    Create.newPeople(7,"Matt","Gadd",62)
    Create.newPeople(7,"Joe","Simmons",12)
    Create.newPeople(7,"Joe","Simmons",5)

    Read.getOne(10,"Joe","Simmons",34,exactCheck = true)

    if ("Joe".contains("Jo"))
      {
        println("Test")
      }


    Read.getOne(10,"Jo","S",34,exactCheck = false)
    Thread.sleep(10000)
  }
}
