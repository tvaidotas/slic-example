

object Testing {
  def main(args: Array[String]): Unit = {
    takeUserInputs()
  }

  def getOneWithUserInputs():Unit = {
    println("Enter The First Name")
    val fNameSearch = scala.io.StdIn.readLine()
    println("Enter The Last Name")
    val lNameSearch = scala.io.StdIn.readLine()
    Read.getOne(1,fNameSearch,lNameSearch,1)
    takeUserInputs()
  }

  def makeOneWithUserInputs():Unit = {
    println("Enter The First Name To Add")
    val fNameInput = scala.io.StdIn.readLine()
    println("Enter The Last Name To Add")
    val lNameInput = scala.io.StdIn.readLine()
    println("Enter The Age To Add")
    val age = scala.io.StdIn.readInt()
    Create.newPeople(1,fNameInput,lNameInput,age)
    takeUserInputs()
  }

  def removeOneWithUserInputs():Unit = {
    println("Enter The First Name To Remove")
    val fNameInput = scala.io.StdIn.readLine()
    println("Enter The Last Name To Add")
    val lNameInput = scala.io.StdIn.readLine()
    println("Enter The Age To Add")
    val age = scala.io.StdIn.readInt()
    Delete.deleteOne(fNameInput,lNameInput)
    takeUserInputs()
  }

  def takeUserInputs (): Unit = {

    println(s"1.ReadAll \n2.ReadOne \n3.Add User \n4.DeleteAll \n5.RemoveOne")
    val userInput = scala.io.StdIn.readLine()
    userInput match {
      case "1" =>
        Read.getAll()
        takeUserInputs()
      case "2" =>
        getOneWithUserInputs()
      case "3" =>
        makeOneWithUserInputs()
      case "4" =>
        Delete.deleteAll()
        takeUserInputs()
      case "5" =>
        removeOneWithUserInputs()
      case _ => println("Invalid Try Again")
        takeUserInputs()
    }
  }
}
