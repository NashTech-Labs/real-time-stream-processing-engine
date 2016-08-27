curl -XPOST  localhost:9200/analysis_index -d '{  
 "mappings": {
      "_default_": {
         "dynamic":"strict",
        "properties": {
          "author_username": {
            "type": "keyword"
           },
          "clean_text": {
            "type": "text"
          },
          "created_at": {
            "type": "date",
            "format":"basic_date||date_optional_time||EEE, dd MMM yyyy HH:mm:ss Z"
          },
          "media_type": {
            "type": "keyword"
          },
          "retweets": {
            "type": "long"
          },
          "sentiment": {
            "type": "keyword"
          },
          "text": {
            "type": "text"
          },
          "id": {
            "type": "keyword"
          }
        }
      }
    }
  }'
