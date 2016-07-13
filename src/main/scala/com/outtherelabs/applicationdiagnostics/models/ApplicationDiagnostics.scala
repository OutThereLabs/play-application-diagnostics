package com.outtherelabs.applicationdiagnostics.models

import play.api.libs.json.Json

case class ApplicationDiagnostics(
  status: String,
  applicationName: String,
  hostName: String,
  versions: Map[String, String],
  env: Map[String, String],
  config: Map[String, String]
)

object ApplicationDiagnostics {
  implicit val jsonWrites = Json.writes[ApplicationDiagnostics]
}
