scalaVersion := "2.11.8"

val root = Project(id = "scala-cv-part1", base = file("."))
.settings(
	name := """scala-cv-part1""",
	version := "0.0.1",
	scalaVersion := "2.11.8",

	libraryDependencies ++= Seq(
	  "com.typesafe.akka" %% "akka-stream" % "2.4.6"
	),
	
	javaCppPresetLibs ++= Seq(
	  "ffmpeg" -> "2.8.1"
	),

	// For Swing
	libraryDependencies := {
	  CrossVersion.partialVersion(scalaVersion.value) match {
	    // if scala 2.11+ is used, add dependency on scala-xml module
	    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
	      libraryDependencies.value ++ Seq(
		"org.scala-lang.modules" %% "scala-swing" % "2.0.0-M2")
	    case _ =>
	      // or just libraryDependencies.value if you don't depend on scala-swing
	      libraryDependencies.value :+ "org.scala-lang" % "scala-swing" % scalaVersion.value
	  }
	},
	// Allows us to stop when using run without killing SBT
	fork in run := true
)
