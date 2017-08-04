name := """play-application-diagnostics"""

organization := "com.outtherelabs"

buildInfoPackage := "com.outtherelabs"

buildInfoOptions += BuildInfoOption.Traits("com.outtherelabs.applicationdiagnostics.models.BuildInformation")

parallelExecution in Test := false

lazy val root = (project in file(".")).enablePlugins(BuildInfoPlugin)

version := "0.2.0"

scalaVersion := "2.12.3"

crossScalaVersions := Seq("2.11.8", "2.12.3")

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.6.2",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.1" % Test
)

scalacOptions := Seq(
  "-encoding", "UTF-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked"
)

// Test coverage configuration
coverageMinimum := 100
coverageFailOnMinimum := true

bintrayOrganization := Some("outtherelabs")

bintrayPackageLabels := Seq("playframework", "scala", "diagnostics")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

pomExtra in Global := {
  <url>https://github.com/OutThereLabs/play-application-diagnostics</url>
  <scm>
    <connection>scm:git:github.com:OutThereLabs/play-application-diagnostics.git</connection>
    <developerConnection>scm:git:github.com:OutThereLabs/play-application-diagnostics.git</developerConnection>
    <url>git@github.com:OutThereLabs/play-application-diagnostics.git</url>
  </scm>
  <developers>
    <developer>
      <id>jtescher</id>
      <name>Julian Tescher</name>
      <url>https://github.com/jtescher/</url>
    </developer>
  </developers>
}
