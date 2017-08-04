package com.outtherelabs.applicationdiagnostics.controllers

import com.outtherelabs.applicationdiagnostics.services.ApplicationDiagnosticsService
import javax.inject.Inject
import play.api.libs.json.Json
import play.api.mvc.{ Action, AnyContent, InjectedController, Request }

class ApplicationDiagnosticsController @Inject() (service: ApplicationDiagnosticsService) extends InjectedController {

  def index(token: String): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    service.getDiagnosticsFromToken(token) match {
      case Some(applicationDiagnostics) => Ok(Json.obj("applicationDiagnostics" -> applicationDiagnostics))
      case None => Unauthorized("Incorrect application diagnostics token")
    }
  }

}
