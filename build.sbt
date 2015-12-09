
name := "whereabouts"

lazy val commonSettings = Seq(
  organization := "com.whereabouts",
  version := "0.1.0",
  scalaVersion := "2.11.6",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

)


lazy val akkaV = "2.3.9"
lazy val sprayV = "1.3.3"

lazy val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "org.specs2" %% "specs2-core" % "2.3.11" % "test"
)

lazy val akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-slf4j" % akkaV,
  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test"
)

lazy val sprayDependencies = Seq(
  "io.spray" %% "spray-can" % sprayV,
  "io.spray" %% "spray-routing" % sprayV,
  "io.spray" %% "spray-testkit" % sprayV % "test"
)

lazy val loggers = Seq(
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)




lazy val whereaboutsApi = project
  .in(file("whereabouts-api"))
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= testDependencies)
  .dependsOn(whereaboutsMessages, whereaboutsServices)

lazy val whereaboutsMessages = project
  .in(file("whereabouts-messages"))
  .settings(commonSettings: _*)

lazy val whereaboutsServices = project
  .in(file("whereabouts-services"))
  .settings(commonSettings: _*).dependsOn(whereaboutsMessages)





