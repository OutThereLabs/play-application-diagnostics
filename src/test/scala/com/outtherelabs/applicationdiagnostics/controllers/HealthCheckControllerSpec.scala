package com.outtherelabs.applicationdiagnostics.controllers

import akka.stream.Materializer
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.Helpers._
import play.api.test.{ FakeRequest, Injecting }

class HealthCheckControllerSpec extends PlaySpec with GuiceOneAppPerSuite with Injecting {
  implicit private val mat = inject[Materializer]
  private val controller = inject[HealthCheckController]

  "#index" should {
    "return OK if healthy" in {
      val response = call(controller.index, FakeRequest(GET, "/health-check"))

      status(response) mustBe OK
      contentType(response) mustBe Some(TEXT)
      contentAsString(response) must include("OK")
    }
  }

}
