

object Testing {
  def main(args: Array[String]): Unit = {
    Create.newPeople(1,"John","Smith",57)
    Delete.deleteAll()
    Create.newPeople(1,"John","Smith",27)
    Read.getAll()
  }
}
