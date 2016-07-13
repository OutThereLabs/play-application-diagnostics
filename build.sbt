name := """play-application-diagnostics"""

organization := "com.outtherelabs"

buildInfoPackage := "com.outtherelabs"

buildInfoOptions += BuildInfoOption.Traits("com.outtherelabs.applicationdiagnostics.models.BuildInformation")

parallelExecution in Test := false

lazy val root = (project in file(".")).enablePlugins(BuildInfoPlugin)

version := "0.1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.5.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

// Test coverage configuration
coverageMinimum := 100
coverageFailOnMinimum := true
