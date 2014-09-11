
scalaVersion := "2.11.2"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "spray repo" at "http://repo.spray.io"
)

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.2.6",
  "io.spray" %% "spray-routing" % "1.3.1",
  "io.spray" %% "spray-testkit" % "1.3.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.0" % "test"
)
