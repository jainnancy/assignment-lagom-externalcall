package main.scala.edu.knoldus.crud.impl


import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import edu.knoldus.crud.models.User
import main.scala.edu.knoldus.crud.api.EmployeeService
import main.scala.edu.knoldus.crud.models.Employee
import main.scala.edu.knoldus.user.api.CrudlagomService

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class EmployeeServiceImpl(crudlagomService: CrudlagomService) extends EmployeeService {

  override def addEmployee: ServiceCall[Employee, ListBuffer[Employee]] = ServiceCall {
    request =>
      val employee = User(request.userId, request.userName, request.age)
      val addEmployee = crudlagomService.addUser.invoke(employee)
      val result = Await.result(addEmployee, Duration.Inf)
      val employeeList = result.map(user => Employee(user.userId, user.userName, user.age))
      Future.successful(employeeList)
  }

  override def showEmployee(id: Int): ServiceCall[NotUsed, Employee] = ServiceCall {
    request =>
    val user = crudlagomService.showUser(id).invoke()
    val result = Await.result(user,Duration.Inf)
    val employee = Employee(result.userId, result.userName, result.age)
    Future.successful(employee)
  }

}
