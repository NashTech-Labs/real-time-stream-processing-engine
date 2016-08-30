name := """real-time-stream-processing-engine"""

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
                      "org.apache.spark" %% "spark-streaming" % "2.0.0",
                      "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.0.0",
                      "org.json4s" %% "json4s-native" % "3.4.0",
                      "org.elasticsearch" %% "elasticsearch-spark-20" % "5.0.0-alpha5",
                      "edu.stanford.nlp" % "stanford-corenlp" % "3.6.0" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp")),
                      "org.scalatest"    %% "scalatest" % "3.0.0"
                 )


//resolvers += "clojars" at "https://clojars.org/repo"
//resolvers += "conjars" at "http://conjars.org/repo"
