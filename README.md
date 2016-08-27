#sentiment-analysis-engine

This is an activator project. It describes how to build sentiment analysis engine using Spark Streaming,Stanford CoreNLP, Kafka & Elasticsearch.

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

 Clone the code & run (This is for spark local mode):

        $ git@github.com:knoldus/sentiment-analysis-engine.git
        $ cd sentiment-analysis-engine
        $ bin/activator run

