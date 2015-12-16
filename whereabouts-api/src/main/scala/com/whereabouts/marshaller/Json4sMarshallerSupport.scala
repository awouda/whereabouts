package com.whereabouts.marshaller

import org.json4s.{Formats, DefaultFormats}
import spray.httpx.{Json4sJacksonSupport}


object Json4sProtocol extends Json4sJacksonSupport {
  implicit  def json4sJacksonFormats: Formats = DefaultFormats
}