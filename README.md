#real-time-stream-processing-engine

This is an example of real time stream processing using Spark Streaming, Kafka & Elasticsearch.

![alt tag](/doc/stream_processing.png)

#Pre-Requisites for this project
####Elasticsearch Setup
  i) [Download](https://www.elastic.co/downloads/elasticsearch) the Elasticsearch 5.0.0-alpha5 or latest version  and unzip it.

  ii) Run the following command.

        $ bin/elasticsearch
        
        
####Kafka Setup
 i) [Download](http://kafka.apache.org/downloads.html) the Kafka-0.10.0.1 or latest version  and unzip it.

 ii) Run the following commands for starting Zookeeper & Kafka:
    
        $ bin/zookeeper-server-start.sh config/zookeeper.properties 
        $ bin/kafka-server-start.sh config/server.properties
        
-----------------------------------------------------------------------
###Getting Started:
-----------------------------------------------------------------------

 Clone and run in local mode:

        $ git@github.com:knoldus/sentiment-analysis-engine.git
        $ cd sentiment-analysis-engine
        $ bin/activator run
        
        
##Limitations
1) Elasticsearch 5.0.0-alpha5 is not production ready.
2) Kafka-0.10.0.1 is experimental in current spark version(spark-2.0.0).
