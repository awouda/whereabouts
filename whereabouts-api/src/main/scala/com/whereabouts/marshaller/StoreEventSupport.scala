package com.whereabouts.marshaller

import com.whereabouts.messages.StoreEvent
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

object StoreEventSupport extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val StoreEventFormats = jsonFormat1(StoreEvent)

}
