name := """kafka-tweet-producer"""

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
                         "org.twitter4j" % "twitter4j-stream" % "4.0.4",
                         "com.typesafe.akka" %% "akka-actor" % "2.4.9",
                         "org.jsoup" % "jsoup" % "1.9.2",
                         "org.json4s" %% "json4s-native" % "3.4.0",
                         "ch.qos.logback" % "logback-classic" % "1.1.7",
                         "org.apache.kafka" % "kafka-clients" % "0.10.0.1",
                         "org.scalatest" %% "scalatest" % "3.0.0" % "test"
                         )


