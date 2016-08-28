package com.knoldus.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object StreamingApp extends App {

  val sparkConf = new SparkConf().setAppName("StreamingApp").setMaster("local[*]")
  sparkConf.set("es.index.auto.create", "true")
  sparkConf.set("es.mapping.id", "id")

  val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(5))

  StreamingProcessor.process(ssc, List("tweet_queue"), "analysis_index2", "twitter")
  ssc.start()
  ssc.awaitTermination()

}
