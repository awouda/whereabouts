package com.whereabouts.services.actor

import akka.actor.Actor
import com.whereabouts.messages.{ StoreEventMsg}
import com.whereabouts.services.{FakeEventService, EventService}

class StoreEventsActor extends Actor {

  val eventService:EventService = new FakeEventService()
  override def receive: Receive = {
    case StoreEventMsg(se) => eventService.store(se)
  }
}
