scalaVersion := "2.12.15"

name := "FlinkApp"
organization := "MontevideoLabs"
version := "1.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1";
libraryDependencies += "org.apache.flink" % "flink-core" % "1.15.0"
libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % "1.15.0";
libraryDependencies += "org.apache.flink" % "flink-connector-kafka" % "1.15.0";
libraryDependencies += "org.apache.flink" %% "flink-connector-cassandra" % "1.15.0";
libraryDependencies += "org.apache.flink" % "flink-clients" % "1.15.0";

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}