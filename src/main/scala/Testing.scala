

object Testing {
  def main(args: Array[String]): Unit = {
    takeUserInputs()
  }

  def getOneLengthWithUserInputs():Unit = {
    println("Enter The Length You Are Looking For In Whole Numbers")
    val nameLength = scala.io.StdIn.readInt()
    println("Enter Feild You Would Like To Search For Length In ,Both ,Forename ,Surname")
    val searchType = scala.io.StdIn.readLine()
    searchType.toLowerCase() match {
      case "both" => Read.getOneByLength(1,nameLength,"both")
      case "fname" => Read.getOneByLength(1,nameLength,"fName")
      case "lname" => Read.getOneByLength(1,nameLength,"lName")
    }


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
    val ageInput = scala.io.StdIn.readInt()
    Create.newPeople(1,fNameInput,lNameInput,ageInput)
    takeUserInputs()
  }

  def removeOneWithUserInputs():Unit = {
    println("Enter The First Name To Remove")
    val fNameInput = scala.io.StdIn.readLine()
    println("Enter The Last Name To Remove")
    val lNameInput = scala.io.StdIn.readLine()
    Delete.deleteOne(fNameInput,lNameInput)
    takeUserInputs()
  }

  def takeUserInputs (): Unit = {
    println("Select A Main Action \n1.Read \n2.Create \n3.Update \n4.Delete \n5.Exit")
    val userMainMenu = scala.io.StdIn.readLine()
    userMainMenu match {
      case "1" => readMenu()
      case "2" => addMenu()
      case "3" => updateMenu()
      case "4" => deleteMenu()
      case "5" => println("Exit")
        sys.exit()
      case _ =>
        println("Invalid Choice")
        takeUserInputs()
    }
  }

    def readMenu() {
      println(s"1.ReadAll \n2.ReadOne \n3.ReadOne With Length \n4.<= Back \n5.Exit")
      val userInput = scala.io.StdIn.readLine()
      userInput match {
        case "1" =>
          Read.getAll()
          takeUserInputs()
        case "2" =>
          getOneWithUserInputs()
        case "3" =>
          getOneLengthWithUserInputs()
        case "4" =>
          takeUserInputs()
        case "5" =>
          println("Exit")
          sys.exit()
        case _ => println("Invalid Try Again")
          takeUserInputs()
      }
    }

    def addMenu() {
      println(s"1.Add User \n2.<= Back")
      val userInput = scala.io.StdIn.readLine()
      userInput match {
        case "1" =>
          makeOneWithUserInputs()
          takeUserInputs()
        case "2" =>
          takeUserInputs()
        case _ => println("Invalid Try Again")
          takeUserInputs()
      }
    }
    def updateMenu() {
      println(s"1.Update A User \n2.<= Back")
      val userInput = scala.io.StdIn.readLine()
      userInput match {
        case "1" =>
          takeUserInputs()
        case "2" =>
          takeUserInputs()
        case _ => println("Invalid Try Again")
          takeUserInputs()
      }
    }

    def deleteMenu() {
      println(s"1.DeleteOne \n2.DeleteAll \n3.<= Back")
      val userInput = scala.io.StdIn.readLine()
      userInput match {
        case "1" =>
          removeOneWithUserInputs()
          takeUserInputs()
        case "2" =>
          Delete.deleteAll()
          takeUserInputs()
        case "3" =>
          takeUserInputs()
        case _ => println("Invalid Try Again")
          takeUserInputs()
      }
    }
}
