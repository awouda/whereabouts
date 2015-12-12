package com.whereabouts.messages

/**
  *  Domain classes
  *
  */


trait Event

case class StoreEvent(data:String) extends Event





/**
  * Actor messages
 */

case class StoreEventMsg(se:StoreEvent)


