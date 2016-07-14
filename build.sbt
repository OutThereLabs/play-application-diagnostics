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
