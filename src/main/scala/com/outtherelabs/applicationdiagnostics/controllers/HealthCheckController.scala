package com.outtherelabs.applicationdiagnostics.controllers

import play.api.mvc.{ Action, AnyContent, InjectedController }

class HealthCheckController extends InjectedController {

  def index: Action[AnyContent] = Action {
    Ok("OK")
  }

}
