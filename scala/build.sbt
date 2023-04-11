lazy val root = project
  .in(file("."))
  .settings(
    name := "strange-characters",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := "3.2.2",

    libraryDependencies += "org.scalatest" %% "scalatest-funsuite" % "3.2.15" % Test,
    libraryDependencies += "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.15" % Test,
  )
