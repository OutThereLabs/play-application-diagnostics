# Play Application Diagnostics
[![License][licenseImg]][licenseLink] [![Travis-CI][travisCiImage]][travisCiLink]
[![Coverage][coverallsImg]][coverallsLink] [![Codacy][codacyImg]][codacyLink]
[![Dependencies][versionEyeImg]][versionEyeLink] [![Download][bintrayImg]][bintrayLink]

Application diagnostics endpoints for Play framework.

[licenseImg]: https://img.shields.io/github/license/pathikrit/better-files.svg
[licenseLink]: LICENSE

[travisCiImage]: https://travis-ci.org/OutThereLabs/play-application-diagnostics.svg?branch=master
[travisCiLink]: https://travis-ci.org/OutThereLabs/play-application-diagnostics

[codacyImg]: https://api.codacy.com/project/badge/Grade/cde1cc476d14466dbb80ea325aa090d5
[codacyLink]: https://www.codacy.com/app/otl/play-application-diagnostics

[versionEyeImg]: https://www.versioneye.com/user/projects/5786c8456edb08003f207517/badge.svg?style=flat-square
[versionEyeLink]: https://www.versioneye.com/user/projects/5786c8456edb08003f207517

[coverallsImg]: https://coveralls.io/repos/github/OutThereLabs/play-application-diagnostics/badge.svg?branch=master
[coverallsLink]: https://coveralls.io/github/OutThereLabs/play-application-diagnostics

[bintrayImg]: https://api.bintray.com/packages/outtherelabs/maven/play-application-diagnostics/images/download.svg
[bintrayLink]: https://bintray.com/outtherelabs/maven/play-application-diagnostics/_latestVersion

## Installation

First add [sbt-buildinfo](https://github.com/sbt/sbt-buildinfo) to `project/plugins.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.6.1")
```

Then for Play >= `2.5.x` add the following to `build.sbt`:

```scala
// Application diagnostics library
libraryDependencies += "com.outtherelabs" %% "play-application-diagnostics" % "0.1.0"

// Package name for sbt-buildinfo
buildInfoPackage := "com.yourpackagenamehere"
```

## Configuration

To configure the diagnostics endpoints add the following to `conf/application.conf`:

```scala
## Application Diagnostics
# ~~~~~
# Play application diagnostics
application-diagnostics {
  token = "your-secret-diagnostics-token-here"
  token = ${?APPLICATION_DIAGNOSTICS_TOKEN}
  build-info-package = "com.yourpackagenamehere"
}
```

Then add the endpoints routes to your `conf/routes` file:

```scala
GET     /health-check               com.outtherelabs.applicationdiagnostics.controllers.HealthCheckController.index
GET     /application-diagnostics    com.outtherelabs.applicationdiagnostics.controllers.ApplicationDiagnosticsController.index(token)
```

## Health check response

If you have added the above configuration, then you should be able to now check the health of your application at
[localhost:9000/health-check](http://localhost:9000/health-check)

```shell
$ curl -i localhost:9000/health-check
HTTP/1.1 200 OK
Content-Length: 2
Content-Type: text/plain; charset=utf-8

OK
```

## Application diagnostics response

You should be able to now see the status of your application at
[localhost:9000/application-diagnostics](http://localhost:9000/application-diagnostics)

```shell
$ curl -i localhost:9000/application-diagnostics?token=your-secret-diagnostics-token-here
HTTP/1.1 200 OK
Content-Length: 51364
Content-Type: application/json

{"applicationDiagnostics":{"status":"UP", ...}
```
