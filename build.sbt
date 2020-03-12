name := "slick"
version := "0.1"
scalaVersion := "2.13.1"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "latest.release",
  "org.slf4j" % "slf4j-nop" % "latest.release",
  "com.typesafe.slick" %% "slick-hikaricp" % "latest.release",
  "mysql" % "mysql-connector-java" % "latest.release"
)
