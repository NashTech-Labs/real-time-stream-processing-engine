package com.knoldus.streaming.json


import org.json4s.DefaultFormats
import org.json4s.JsonAST.JValue
import org.json4s.native.Serialization
import org.json4s.native.JsonMethods



object JsonHelper {

  implicit val formats = DefaultFormats

  def write[T <: AnyRef](value: T): String = Serialization.write(value)

  def parse(json:String):JValue = JsonMethods.parse(json)
}

