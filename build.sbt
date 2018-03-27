
name := "assignment-lagom-externalcall"

version := "0.1"

scalaVersion := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"

lazy val `employee-lagom` = (project in file("."))
  .aggregate(`employee-api`,`employee-impl`)

lazy val `employee-api` = (project in file("employee-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `employee-impl` = (project in file("employee-impl"))
  .settings(
    libraryDependencies ++= Seq(
      macwire,
      "edu.knoldus" %% "crud-lagom-impl" % "0.1"
    )
  )
  .enablePlugins(LagomScala)
  .dependsOn(`employee-api`)

lazy val externalService = lagomExternalScaladslProject("external","edu.knoldus" %% "crud-lagom-impl" % "0.1")