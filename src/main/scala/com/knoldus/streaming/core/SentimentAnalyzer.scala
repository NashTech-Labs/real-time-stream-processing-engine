package com.knoldus.streaming.core


import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

import scala.collection.convert.wrapAll._

object SentimentAnalyzer {

  private val props = new Properties()
  props.setProperty("annotators", "tokenize, ssplit, parse, sentiment")
  private val pipeline: StanfordCoreNLP = new StanfordCoreNLP(props)

  def getSentiment(input: String): String = {
    if (Option(input).isDefined && !input.trim.isEmpty) {
      val annotation: Annotation = pipeline.process(input)
      val (_, sentiment) =
        annotation.get(classOf[CoreAnnotations.SentencesAnnotation])
          .map { sentence => (sentence, sentence.get(classOf[SentimentCoreAnnotations.SentimentAnnotatedTree])) }
          .map { case (sentence, tree) => (sentence.toString, getSentimentType(RNNCoreAnnotations.getPredictedClass(tree))) }
          .maxBy { case (sentence, _) => sentence.length }
      sentiment
    } else {
      throw new IllegalArgumentException("Text should not empty or null")
    }
  }


  private def getSentimentType(sentiment: Int): String = sentiment match {
    case x if x == 3 || x == 4 => "positive"
    case x if x == 0 || x == 1 => "negative"
    case 2 => "neutral"
  }
}

// https://github.com/shekhargulati/52-technologies-in-2016/tree/master/03-stanford-corenlp