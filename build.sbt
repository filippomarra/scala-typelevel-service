val Http4sVersion           = "0.21.4"
val CirceVersion            = "0.13.0"
val DoobieVersion           = "0.8.8"
val ScalaLoggingVersion     = "3.9.5"
val LogbackVersion          = "1.3.5"
val TypelevelConfigVersion  = "1.4.2"
val KingProjectorVersion    = "0.10.3"
val BetterMonadicForVersion = "0.3.0"

lazy val scalaTypelevelService = (project in file("."))
  .settings(
    organization := "phil",
    name := "scala-typelevel-service",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      "io.circe"                   %% "circe-generic"       % CirceVersion,
      "org.tpolecat"               %% "doobie-core"         % DoobieVersion,
      "org.tpolecat"               %% "doobie-postgres"     % DoobieVersion,
      "com.typesafe.scala-logging" %% "scala-logging"       % ScalaLoggingVersion,
      "ch.qos.logback"              % "logback-classic"     % LogbackVersion,
      "com.typesafe"                % "config"              % TypelevelConfigVersion
    ) ++ httpDependencies,
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % KingProjectorVersion),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % BetterMonadicForVersion)
  )

lazy val httpDependencies = Seq(
  "org.http4s"     %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"     %% "http4s-blaze-client" % Http4sVersion,
  "org.http4s"     %% "http4s-circe"        % Http4sVersion,
  "org.http4s"     %% "http4s-dsl"          % Http4sVersion,
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Xfatal-warnings"
)

Global / onChangedBuildSource := ReloadOnSourceChanges

outputStrategy := Some(StdoutOutput)
