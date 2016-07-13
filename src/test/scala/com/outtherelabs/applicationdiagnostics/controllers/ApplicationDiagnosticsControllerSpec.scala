package com.outtherelabs.applicationdiagnostics.controllers

import akka.stream.Materializer
import com.outtherelabs.applicationdiagnostics.services.ApplicationDiagnosticsService
import org.scalatestplus.play.{ OneAppPerSuite, PlaySpec }
import play.api.Configuration
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

class ApplicationDiagnosticsControllerSpec extends PlaySpec with OneAppPerSuite {
  implicit val mat = app.injector.instanceOf[Materializer]
  val config = app.injector.instanceOf[Configuration]
  val diagnosticsToken = config.getString("application-diagnostics.token").get
  val applicationDiagnosticsService = app.injector.instanceOf[ApplicationDiagnosticsService]
  val controller = app.injector.instanceOf[ApplicationDiagnosticsController]

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
