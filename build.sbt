
name := "whereabouts"

lazy val commonSettings = Seq(
  organization := "com.whereabouts",
  version := "0.1.0",
  scalaVersion := "2.11.6"
)

lazy val whereaboutsApi = project
  .in(file("whereabouts-api"))
  .settings(commonSettings: _*)


