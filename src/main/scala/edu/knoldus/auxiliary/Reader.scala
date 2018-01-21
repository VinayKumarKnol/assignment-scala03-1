package edu.knoldus.auxiliary

import java.io.File

import org.apache.log4j.Logger
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization.read

import scala.io.Source

object Reader extends App {
  def readFromJSON(): Unit = {
    implicit val formats: DefaultFormats = DefaultFormats
    val log = Logger.getLogger(this.getClass)
    try{
    val bufferedSource = Source.fromFile(new File("Secret.json")).mkString
    val man: Person = read[Person](bufferedSource)
    log.info(s"Details of Person \n")
    log.info(s"-"* FACTOR)
    log.info(s"\nName        : ${man.name}\n")
    log.info(s"Day         : ${man.day}\n")
    log.info(s"Lucky Number: ${man.luckyNumber}\n")
    log.info(s"Salary      : ${man.salary}\n")
    log.info(s"House No.   : ${man.address.houseNo}\n")
    log.info(s"City        : ${man.address.city}\n")
    log.info(s"Country     : ${man.address.country}\n")
    }catch{
    case except: Exception => log.info(except.getMessage)
    }
  }

  readFromJSON()

}
