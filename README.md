# flink-kafka-real-time-pipeline
Pipeline components for real time data processing. Consists on a Flink module written on Scala.

## About the project
This project is the source code for the **First steps towards building a real-time big data processing pipeline with Apache Kafka and Apache Flink with Scala** article. 

The idea is to provide an introduction to Apache Flink and Apache Kafka by building a simple pipeline with a Kafka Queue and a Flink App that sinks the events pushed to Kafka into a Cassandra table.

## Getting set up

### Getting set up with Visual Studio Code

Make sure to have sbt installed in your local environment.

Open up the kafka-flink-cassandra folder in Visual Studio Code. Run the integrated terminal in the kafka-flink-cassandra folder.

Run the command *sbt assembly*

This command will create a new folder in kafka-flink-cassandra called target. You will find the generated fat jar in kafka-flink-cassandra/target/scala-2.12 called FlinkApp-assembly-0.1.jar. This is the file you can feed the flink run command to submit the app. 

### Getting set up with IntelliJ IDEA

Make sure to have sbt installed in your local environment.

Launch IntelliJ IDEA. Select open and choose the kafka-flink-cassandra. Open the IntelliJ terminal on kafka-flick-cassandra and run *sbt assembly*.

This command will create a new folder in kafka-flink-cassandra called target. You will find the generated fat jar in kafka-flink-cassandra/target/scala-2.12 called FlinkApp-assembly-0.1.jar. This is the file you can feed the flink run command to submit the app.

### Setting up the other components

All what is left to do is setting up the local Cassandra instance, the Kafka topic and launching the Flink cluster. All these tasks are explained in detail in the article.



