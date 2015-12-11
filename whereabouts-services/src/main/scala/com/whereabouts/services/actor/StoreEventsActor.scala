package com.whereabouts.services.actor

import akka.actor.{ActorLogging, Actor}
import com.whereabouts.messages.{ StoreEventMsg}
import com.whereabouts.services.{FakeEventService, EventService}

class StoreEventsActor extends Actor with ActorLogging {

  log.info("instantiated actor"+self.path.name)

  val eventService:EventService = new FakeEventService()

  override def receive: Receive = {

    case StoreEventMsg(se) =>
      log.info("got a store event"+se)

      eventService.store(se)
      sender ! "stored "+se.data
  }
}
