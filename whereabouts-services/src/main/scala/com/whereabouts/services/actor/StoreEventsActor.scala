package com.whereabouts.services.actor

import akka.actor.Actor
import akka.actor.Actor.Receive
import com.whereabouts.messages.{StoreEvent, StoreEventMsg}
import com.whereabouts.services.{EventServiceImpl, EventService}

class StoreEventsActor extends Actor {

  val eventService:EventService = new EventServiceImpl()
  override def receive: Receive = {
    case StoreEventMsg(se) => eventService.store(se)
  }
}
