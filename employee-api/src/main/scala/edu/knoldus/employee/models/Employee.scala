package main.scala.edu.knoldus.crud.models

import play.api.libs.json.{Format, Json}

object Employee {
  implicit val format: Format[Employee] = Json.format[Employee]
}

case class Employee(userId: Int, userName: String, age: Int)
