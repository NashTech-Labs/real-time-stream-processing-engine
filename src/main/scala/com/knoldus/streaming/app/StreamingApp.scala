package com.knoldus.streaming.app

import com.knoldus.streaming.core.SentimentAnalyzer
import com.knoldus.streaming.json.JsonHelper._
import com.knoldus.streaming.util.DateUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.elasticsearch.spark._


object StreamingApp extends App {

  val kafkaParams = Map(
    "bootstrap.servers" -> "localhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "auto.offset.reset" -> "earliest",
    "group.id" -> "tweet-consumer"
  )
  val preferredHosts = LocationStrategies.PreferConsistent
  val topics = List("tweet_queue")

  val sparkConf = new SparkConf().setAppName("StreamingApp").setMaster("local[*]")
  sparkConf.set("es.index.auto.create", "true")
  sparkConf.set("es.mapping.id", "id")


  val ssc = new StreamingContext(sparkConf, Seconds(5))

  // val offsets = Map(new TopicPartition("tweet_queue", 0) -> 2L) // leave for now

  val messages: InputDStream[ConsumerRecord[String, String]] =
    KafkaUtils.createDirectStream[String, String](
      ssc,
      preferredHosts,
      ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
    )
  val tweets = messages.map { record => parse(record.value()).extract[Map[String, String]] }

  val tweetSentiments: DStream[Map[String, String]] =
    tweets.map { tweet =>
      tweet + ("sentiment" -> SentimentAnalyzer.getSentiment(tweet("text"))) + ("created_at" -> DateUtil.getESDateFormat(tweet("created_at")))
    }
  tweetSentiments.foreachRDD {
    tweetRDD => tweetRDD.saveToEs("analysis_index/twitter")
  }

  ssc.start()
  ssc.awaitTermination()

}

// bin/elasticsearch -Ehttp.type=netty3
//https://www.elastic.co/guide/en/elasticsearch/reference/master/breaking-changes-5.0.html
//https://discuss.elastic.co/t/write-es-error-with-spark-2-0-release/56967/6
//https://github.com/apache/spark/blob/master/external/kafka-0-10/src/test/scala/org/apache/spark/streaming/kafka010/DirectKafkaStreamSuite.scala