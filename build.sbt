name := "actors"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaVersion = "2.4.11"
  val catsVersion = "0.7.2"
  val typesafeConfigVersion = "1.3.1"
  val scalaTestVersion = "2.2.6"
  val scalaMockVersion = "3.3.0"
  val logbackVersion = "1.1.7"
  val scalaLoggingVersion = "3.5.0"
  val pegdownVersion = "1.6.0"
  val scalaCacheVersion = "0.9.2"

  Seq(
    // Akka
    "com.typesafe.akka" %% "akka-actor" % "2.4.12",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,

    // Config
    "com.typesafe" % "config" % typesafeConfigVersion,

    // Cats
    "org.typelevel" %% "cats" % catsVersion,

    // Logging
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,

    // Tests
    "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockVersion % "test",
    "org.pegdown" % "pegdown" % pegdownVersion % "test"
  )
}

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-language:_",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-language:postfixOps",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-unused-import"
  //"-Y"
)

enablePlugins(JavaServerAppPackaging)

packageName in Universal := name.value
publishArtifact in(Compile, packageDoc) := false

crossPaths := false

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")

target in Universal := file("artifact")
    