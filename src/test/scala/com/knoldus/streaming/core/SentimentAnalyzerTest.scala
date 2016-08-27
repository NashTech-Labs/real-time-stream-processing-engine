package com.knoldus.streaming.core

import org.scalatest.{Matchers, WordSpecLike}


class SentimentAnalyzerTest extends WordSpecLike with Matchers{

  "SentimentAnalyzer " must {

    "analyze positive sentiment" in {
      SentimentAnalyzer.getSentiment("I am very happy with spark") shouldBe "positive"
    }

    "analyze negative sentiment" in {
      SentimentAnalyzer.getSentiment("I am very frustrated") shouldBe "negative"
    }


    "analyze neutral sentiment" in {
      SentimentAnalyzer.getSentiment("I am a developer" ) shouldBe "neutral"
    }

  }



}
