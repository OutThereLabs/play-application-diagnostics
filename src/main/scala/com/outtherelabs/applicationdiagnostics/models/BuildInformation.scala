package com.outtherelabs.applicationdiagnostics.models

trait BuildInformation {
  val name: String
  val version: String
  val scalaVersion: String
  val sbtVersion: String
}
