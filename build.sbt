name := "whereabouts"

def WhereaboutsProject(name: String): Project =
  Project(name, file(name)).settings(
    version := "0.1.0",
    organization := "com.whereabouts",
    fork in Test := true,
    scalaVersion := "2.11.7",
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
  )


// enables the sbt-pack "pack" command to generate a distribution
packAutoSettings

lazy val akkaV = "2.4.0"
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
  "io.spray" %% "spray-testkit" % sprayV % "test",
  "io.spray" %% "spray-json" % "1.3.2"
)

lazy val logDependencies = Seq(
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)


lazy val gatlingDependencies = Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.5" % "test",
  "io.gatling" % "gatling-test-framework" % "2.1.5" % "test"
)

lazy val apiDependencies = Seq("org.json4s" %% "json4s-jackson" % "3.3.0"
)



lazy val whereaboutsApi = WhereaboutsProject("whereabouts-api")
  .settings(libraryDependencies ++= (
    testDependencies ++
      akkaDependencies ++
      sprayDependencies ++
      logDependencies ++
      apiDependencies ++
      gatlingDependencies))
  .dependsOn(whereaboutsMessages, whereaboutsServices)

lazy val whereaboutsMessages = WhereaboutsProject("whereabouts-messages")

lazy val whereaboutsServices = WhereaboutsProject("whereabouts-services")
  .settings(libraryDependencies ++= (akkaDependencies ++ logDependencies))
  .dependsOn(whereaboutsMessages)