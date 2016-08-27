package com.knoldus.streaming.util

import java.text.SimpleDateFormat
import java.util.Date


object DateUtil {


  private val dateFormats = List(
    "yyyyMMdd'T'HHmmss.SSSZ",
    "EEE, dd MMM yyyy HH:mm:ss Z",
    "yyyy-MM-dd HH:mm:ss",
    "EEE MMM dd HH:mm:ss Z yyyy",
    "MMM dd, yyyy, HH:mm a",
    "MMM dd, yyyy HH:mm a",
    "yyyy-MM-dd'T'HH:mm:ss",
    "dd MMM yyyy HH:mm:ss:S Z",
    "E MMM dd HH:mm:ss z yyyy",
    "dd MMM yyyy HH:mm:ss:SSS",
    "dd MMM yyyy H:mm:ss:SSS",
    "MM-dd-yyyy HH:mm:ss:SSS",
    "MM/dd/yyyy HH:mm:ss:SSS",
    "dd/MM/yyyy HH:mm:ss:SSS",
    "dd-MM-yyyy HH:mm:ss:SSS",
    "MMM/dd/yyyy HH:mm:ss:SSS",
    "MMM-dd-yyyy HH:mm:ss:SSS",
    "dd-MMM-yyyy HH:mm:ss:SSS",
    "MM-dd-yyyy H:mm:ss:SSS",
    "MM/dd/yyyy H:mm:ss:SSS",
    "dd/MM/yyyy H:mm:ss:SSS",
    "dd-MM-yyyy H:mm:ss:SSS",
    "MMM/dd/yyyy H:mm:ss:SSS",
    "MMM-dd-yyyy H:mm:ss:SSS",
    "dd-MMM-yyyy H:mm:ss:SSS",
    "MM-dd-yyyy HH:mm:ss",
    "MM/dd/yyyy HH:mm:ss",
    "dd/MM/yyyy HH:mm:ss",
    "dd-MM-yyyy HH:mm:ss",
    "MMM/dd/yyyy HH:mm:ss",
    "MMM-dd-yyyy HH:mm:ss",
    "dd-MMM-yyyy HH:mm:ss",
    "MM-dd-yyyy H:mm:ss",
    "MM/dd/yyyy H:mm:ss",
    "dd/MM/yyyy H:mm:ss",
    "dd-MM-yyyy H:mm:ss",
    "MMM/dd/yyyy H:mm:ss",
    "MMM-dd-yyyy H:mm:ss",
    "dd-MMM-yyyy H:mm:ss",
    "yyyy-MM-dd",
    "MM-dd-yyyy",
    "MM/dd/yyyy",
    "dd/MM/yyyy",
    "dd-MM-yyyy",
    "MMM/dd/yyyy",
    "MMM-dd-yyyy",
    "dd-MMM-yyyy")


  private  val esDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

  def getESDateFormat(dateString: String): String = {
    def getDate(dateFormats: Seq[String], dateString: String): String = {
      try {
        val dateFormat = new SimpleDateFormat(dateFormats.head)
        val date = dateFormat.parse(dateString)
        esDateFormat.format(date)
      } catch {
        case _ if (dateFormats.size > 1) => getDate(dateFormats.tail, dateString)
        case _: Exception => esDateFormat.format(new Date())
      }
    }
    getDate(dateFormats, dateString)
  }
}
