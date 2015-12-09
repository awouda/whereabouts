package com.whereabouts.messages

trait Event

case class StoreEvent(data:String) extends Event
case class StoreEventMsg(se:StoreEvent)


