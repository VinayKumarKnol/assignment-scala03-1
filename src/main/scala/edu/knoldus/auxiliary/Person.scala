package edu.knoldus.auxiliary

import java.io.{File, PrintWriter}
import java.util.Date

import org.apache.log4j.Logger
import org.json4s._

@SerialVersionUID(UID)
class Person(val name: String, @transient val luckyNumber: Int = LUCKYNUMBER, @transient val day: Date = new Date(),
             val salary: Double, val address: Address)

class Address(val houseNo: String, val city: String, val country: String)


object Person extends App {
  val residentOf: Address = new Address(houseNo = "Type/2 - 64", city = "New Delhi", country = "India")
  val aPerson: Person = new Person(name = "Vinay Kumar", salary = SALARY, address = residentOf)
  writeToJSON(aPerson)

  def writeToJSON(aPerson: Person): Unit = {
    implicit val formats: DefaultFormats = DefaultFormats
    val log = Logger.getLogger(this.getClass)
    val jsonString = org.json4s.jackson.Serialization.writePretty(aPerson)
    try {
      val inputToFile = new PrintWriter(new File("Secret.json"))
      inputToFile.write(jsonString)
      log.info("\nInput has been recorded to file: Secret.json")
      inputToFile.close()
    }
    catch {
      case notFound: Exception => log.info(notFound.getMessage)
    }

  }

}
