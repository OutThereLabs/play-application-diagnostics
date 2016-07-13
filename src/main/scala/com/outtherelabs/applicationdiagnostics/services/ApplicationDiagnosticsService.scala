package com.outtherelabs.applicationdiagnostics.services

import com.outtherelabs.applicationdiagnostics.models.{ ApplicationDiagnostics, BuildInformation }
import java.net.InetAddress
import javax.inject.Inject
import play.api.{ Configuration, Environment }
import scala.reflect.runtime.universe

class ApplicationDiagnosticsService @Inject() (config: Configuration, env: Environment) {
  val buildInfoPackage = config.getString("application-diagnostics.build-info-package").getOrElse("com.outtherelabs")
  val diagnosticsToken = config.getString("application-diagnostics.token").get

  val runtimeMirror = universe.runtimeMirror(env.classLoader)
  val module = runtimeMirror.staticModule(s"$buildInfoPackage.BuildInfo")
  val buildInfo: BuildInformation = runtimeMirror.reflectModule(module).instance.asInstanceOf[BuildInformation]

  def getDiagnosticsFromToken(token: String): Option[ApplicationDiagnostics] = {
    if (token == diagnosticsToken) {
      Some(getApplicationDiagnostics)
    } else {
      None
    }
  }

  private def getApplicationDiagnostics: ApplicationDiagnostics = ApplicationDiagnostics(
    status = "UP",
    applicationName = buildInfo.name,
    hostName = InetAddress.getLocalHost.getHostName,
    versions = getVersions,
    env = sys.env,
    config = config.entrySet.map(m => m._1 -> m._2.toString).toMap
  )

  private def getVersions: Map[String, String] = Map(
    "app" -> buildInfo.version,
    "scala" -> buildInfo.scalaVersion,
    "sbt" -> buildInfo.sbtVersion,
    "java" -> System.getProperty("java.version")
  )

}
