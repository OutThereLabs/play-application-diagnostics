package com.outtherelabs.applicationdiagnostics.services

import com.outtherelabs.applicationdiagnostics.models.{ ApplicationDiagnostics, BuildInformation }
import java.net.InetAddress
import java.util.UUID
import javax.inject.Inject
import play.api.{ Configuration, Environment }
import scala.reflect.runtime.universe

class ApplicationDiagnosticsService @Inject() (config: Configuration, env: Environment) {
  private val buildInfoPackage = config.getOptional[String]("application-diagnostics.build-info-package")
    .getOrElse("com.outtherelabs")
  val diagnosticsToken: String = config.getOptional[String]("application-diagnostics.token")
    .getOrElse(UUID.randomUUID.toString)

  private val runtimeMirror = universe.runtimeMirror(env.classLoader)
  private val module = runtimeMirror.staticModule(s"$buildInfoPackage.BuildInfo")
  private val buildInfo: BuildInformation = runtimeMirror.reflectModule(module).instance.asInstanceOf[BuildInformation]

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
    config = config.entrySet.map { case (configKey, configValue) => configKey -> configValue.toString }.toMap
  )

  private def getVersions: Map[String, String] = Map(
    "app" -> buildInfo.version,
    "scala" -> buildInfo.scalaVersion,
    "sbt" -> buildInfo.sbtVersion,
    "java" -> System.getProperty("java.version")
  )

}
