
package main.scala.edu.knoldus.crud.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import main.scala.edu.knoldus.crud.models.Employee

import scala.collection.mutable.ListBuffer

object EmployeeService {
  val TOPIC_NAME = "users"
}

trait EmployeeService extends Service {

  def showEmployee(id: Int): ServiceCall[NotUsed, Employee]

  def addEmployee: ServiceCall[Employee, ListBuffer[Employee]]

  override final def descriptor: Descriptor = {

    import Service._
    named("external")
      .withCalls(
        restCall(Method.POST, "/api/adduser", addEmployee _),
        restCall(Method.GET, "/api/showuser/:id", showEmployee _)
      )
      .withAutoAcl(true)
  }
}
