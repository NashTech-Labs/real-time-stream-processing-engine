package com.knoldus.streaming.persist

import org.apache.spark.streaming.dstream.DStream
import org.elasticsearch.spark._


object PersistenceFactory {

  def persist(mapDStream: DStream[Map[String, String]], indexName: String, `type`: String): Unit =
    mapDStream.foreachRDD {
      tweetRDD => tweetRDD.saveToEs(indexName + "/" + `type`)
    }

}
