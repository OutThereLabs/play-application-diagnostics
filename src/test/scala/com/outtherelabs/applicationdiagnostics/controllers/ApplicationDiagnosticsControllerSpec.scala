package com.outtherelabs.applicationdiagnostics.controllers

import akka.stream.Materializer
import com.outtherelabs.applicationdiagnostics.services.ApplicationDiagnosticsService
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Configuration
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test.{ FakeRequest, Injecting }

class ApplicationDiagnosticsControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {
  implicit private val mat = inject[Materializer]
  private val config = inject[Configuration]
  private val diagnosticsToken = config.get[String]("application-diagnostics.token")
  private val applicationDiagnosticsService = inject[ApplicationDiagnosticsService]
  private val controller = inject[ApplicationDiagnosticsController]

  "#index" should {
    "render application diagnostics if authorized" in {
      val applicationDiagnostics = applicationDiagnosticsService.getDiagnosticsFromToken(diagnosticsToken)

      val request = FakeRequest(GET, s"/application-diagnostics?token=$diagnosticsToken")
      val response = call(controller.index(diagnosticsToken), request)

      status(response) mustBe OK
      contentType(response) mustBe Some(JSON)
      contentAsJson(response) mustBe Json.obj("applicationDiagnostics" -> applicationDiagnostics)
    }

    "return UNAUTHORIZED if token is missing" in {
      val request = FakeRequest(GET, "/application-diagnostics?token=INCORRECT_TOKEN")
      val response = call(controller.index("INCORRECT_TOKEN"), request)

      status(response) mustBe UNAUTHORIZED
      contentType(response) mustBe Some(TEXT)
      contentAsString(response) mustBe "Incorrect application diagnostics token"
    }
  }

}
