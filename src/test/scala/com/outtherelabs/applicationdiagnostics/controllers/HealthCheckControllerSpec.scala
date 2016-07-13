package com.outtherelabs.applicationdiagnostics.controllers

import akka.stream.Materializer
import org.scalatestplus.play.{ OneAppPerSuite, PlaySpec }
import play.api.test.FakeRequest
import play.api.test.Helpers._

class HealthCheckControllerSpec extends PlaySpec with OneAppPerSuite {
  implicit val mat = app.injector.instanceOf[Materializer]
  val controller = app.injector.instanceOf[HealthCheckController]

  "#index" should {
    "return OK if healthy" in {
      val response = call(controller.index, FakeRequest(GET, "/health-check"))

      status(response) mustBe OK
      contentType(response) mustBe Some(TEXT)
      contentAsString(response) must include("OK")
    }
  }

}
