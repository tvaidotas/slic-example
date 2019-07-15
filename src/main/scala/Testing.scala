

object Testing {
  def main(args: Array[String]): Unit = {
    takeUserInputs()

//    Create.newPeople(1,"John","Smith",59)
//    Delete.deleteAll()
//    Create.newPeople(1,"John","Smith",27)
//    Read.getAll()
//    Read.getOne(10,"John","Smith",34,exactCheck = true)
//    Create.newPeople(7,"Joe","Simmons",62)
//    Create.newPeople(7,"Matt","Gadd",62)
//    Create.newPeople(7,"Joe","Simmons",12)
//    Create.newPeople(7,"Joe","Simmons",5)
//    Read.getOne(10,"Joe","Simmons",34,exactCheck = true)
//
//    Read.getOne(10,"Jo","S",34,exactCheck = false)
//    Thread.sleep(10000)
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

  def takeUserInputs (): Unit ={

    println("   ____         __________                     .___    _____   .__   .__                       \n  /_   |        \\______   \\  ____  _____     __| _/   /  _  \\  |  |  |  |                      \n   |   |         |       _/_/ __ \\ \\__  \\   / __ |   /  /_\\  \\ |  |  |  |                      \n   |   |         |    |   \\\\  ___/  / __ \\_/ /_/ |  /    |    \\|  |__|  |__                    \n   |___|    /\\   |____|_  / \\___  >(____  /\\____ |  \\____|__  /|____/|____/                    \n            \\/          \\/      \\/      \\/      \\/          \\/                                 \n________        __________                     .___ ________                                   \n\\_____  \\       \\______   \\  ____  _____     __| _/ \\_____  \\    ____    ____                  \n /  ____/        |       _/_/ __ \\ \\__  \\   / __ |   /   |   \\  /    \\ _/ __ \\                 \n/       \\        |    |   \\\\  ___/  / __ \\_/ /_/ |  /    |    \\|   |  \\\\  ___/                 \n\\_______ \\  /\\   |____|_  / \\___  >(____  /\\____ |  \\_______  /|___|  / \\___  >                \n        \\/  \\/          \\/      \\/      \\/      \\/          \\/      \\/      \\/                 \n________           _____       .___    .___ __________                                         \n\\_____  \\         /  _  \\    __| _/  __| _/ \\______   \\  ____  _______   ______  ____    ____  \n  _(__  <        /  /_\\  \\  / __ |  / __ |   |     ___/_/ __ \\ \\_  __ \\ /  ___/ /  _ \\  /    \\ \n /       \\      /    |    \\/ /_/ | / /_/ |   |    |    \\  ___/  |  | \\/ \\___ \\ (  <_> )|   |  \\\n/______  /  /\\  \\____|__  /\\____ | \\____ |   |____|     \\___  > |__|   /____  > \\____/ |___|  /\n       \\/   \\/          \\/      \\/      \\/                  \\/              \\/              \\/ \n   _____        ________           .__             __               _____   .__   .__          \n  /  |  |       \\______ \\    ____  |  |    ____  _/  |_   ____     /  _  \\  |  |  |  |         \n /   |  |_       |    |  \\ _/ __ \\ |  |  _/ __ \\ \\   __\\_/ __ \\   /  /_\\  \\ |  |  |  |         \n/    ^   /       |    `   \\\\  ___/ |  |__\\  ___/  |  |  \\  ___/  /    |    \\|  |__|  |__       \n\\____   |   /\\  /_______  / \\___  >|____/ \\___  > |__|   \\___  > \\____|__  /|____/|____/       \n     |__|   \\/          \\/      \\/            \\/             \\/          \\/                    ")
    val userInput = scala.io.StdIn.readLine()
    userInput match {
      case "1" => Read.getAll()
      case "2" => getOneWithUserInputs()
      case "3" => makeOneWithUserInputs()
      case "4" => Delete.deleteAll()
      case _   => println("Invalid Try Again")
    }


  }
}
