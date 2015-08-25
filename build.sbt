val app = crossProject.settings(
  name := "Client-Server Base Project",
  version := "1.0",
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint"
  ),
  unmanagedSourceDirectories in Compile +=
    baseDirectory.value / "shared" / "main" / "scala",
  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "scalatags" % "0.5.2",
    "com.lihaoyi" %%% "upickle" % "0.3.4",
    "com.lihaoyi" %%% "autowire" % "0.2.5",
    "com.lihaoyi" %%% "utest" % "0.3.1" % "test",
    "org.scala-lang.modules" %% "scala-async" % "0.9.5"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
).jsSettings(
  workbenchSettings:_*    // for compiler logs in browser console
).jsSettings(
  name := "Client",
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0",
    "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
  ),
  skip in packageJSDependencies := false,   // collect all JavaScript dependencies in one file
  scalaJSStage in Global := FastOptStage,   // to use Node.js or PhantomJS for tests
  jsDependencies += RuntimeDOM,             // to use PhantomJS for tests

  /* config for workbench plugin*/
  bootSnippet := "simple.Client().main(document.getElementById('contents'))",
  //updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)
  refreshBrowsers <<= refreshBrowsers.triggeredBy(fastOptJS in Compile)
).jvmSettings(
  Revolver.settings:_*    // for automatic server restart
).jvmSettings(
  name := "Server",
  libraryDependencies ++= Seq(
    "io.spray" %% "spray-can" % "1.3.2",
    "io.spray" %% "spray-routing" % "1.3.2",
    "com.typesafe.akka" %% "akka-actor" % "2.3.12"
  )
)

lazy val appJS = app.js
lazy val appJVM = app.jvm.settings(
  /* for development */
  (resources in Compile) += (fastOptJS in (appJS, Compile)).value.data
  /* for production */
  //(resources in Compile) += (fullOptJS in (appJS, Compile)).value.data
)
