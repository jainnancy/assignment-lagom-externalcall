package main.scala.edu.knoldus.crud.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader, LagomServer}
import com.softwaremill.macwire.wire
import main.scala.edu.knoldus.crud.api.EmployeeService
import main.scala.edu.knoldus.user.api.CrudlagomService
import play.api.libs.ws.ahc.AhcWSComponents

class EmployeeLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication = {
    new EmployeeApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }
  }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new EmployeeApplication(context) with LagomDevModeComponents
}

abstract class EmployeeApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {
  override lazy val lagomServer: LagomServer = serverFor[EmployeeService](wire[EmployeeServiceImpl])
  lazy val externalService = serviceClient.implement[CrudlagomService]
}
