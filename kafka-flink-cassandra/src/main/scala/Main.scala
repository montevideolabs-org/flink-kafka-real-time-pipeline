import org.apache.flink.api.common.eventtime.WatermarkStrategy
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.connector.kafka.source.KafkaSource
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.cassandra.CassandraSink
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.apache.flink.util.Collector
import org.apache.flink.streaming.api.scala._

object Main extends App{

    //Environment init. Checkpointing configuration.
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.enableCheckpointing(60000, CheckpointingMode.EXACTLY_ONCE)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)

    //Building KafkaSource.
    val source : KafkaSource[String] = KafkaSource.builder()
      .setBootstrapServers("localhost:9092")
      .setTopics("flink-input")
      .setGroupId("group1")
      .setStartingOffsets(OffsetsInitializer.earliest())
      .setValueOnlyDeserializer(new SimpleStringSchema())
      .build()

    //CassandraSink only works with Tuples or POJOs. In Scala, it only works for Tuples.
    val tuples = env.fromSource(source, WatermarkStrategy.noWatermarks(), "KafkaSource")
      .flatMap(
        new FlatMapFunction[String,Tuple1[String]]{
          override def flatMap(t: String, collector: Collector[Tuple1[String]]): Unit =
            collector.collect(Tuple1[String](t))
        }
      )

    //actual sinking
    CassandraSink.addSink(tuples)
      .setHost("127.0.0.1")
      .setQuery("INSERT INTO cassandraKafkaSink.messages (payload) values (?);")
      .build()
      .name("flinkTestSink")

    //executing Flink job
    env.execute("Flink test")

}
