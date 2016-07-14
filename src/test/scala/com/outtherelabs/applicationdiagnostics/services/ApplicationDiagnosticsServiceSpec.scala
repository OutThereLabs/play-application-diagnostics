package com.outtherelabs.applicationdiagnostics.services

import com.typesafe.config.ConfigFactory
import org.scalatestplus.play.{ OneAppPerSuite, PlaySpec }
import play.api.{ Configuration, Environment }

class ApplicationDiagnosticsServiceSpec extends PlaySpec with OneAppPerSuite {
  val env = app.injector.instanceOf[Environment]

  "#diagnosticsToken" should {
    "default to something secure if not provided" in {
      val config = ConfigFactory.load
      val configWithoutToken = config.withoutPath("application-diagnostics.token")
      val service = new ApplicationDiagnosticsService(Configuration(configWithoutToken), env)

      service.diagnosticsToken mustBe a[String]
    }
  }

}
