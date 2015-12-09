package com.whereabouts.services

import com.whereabouts.messages.StoreEvent

trait EventService {

  def store(se:StoreEvent)

}

class EventServiceImpl extends EventService{

  //Logger logger = LoggerFactory.getLogger(HelloWorld.class);

  def store(se:StoreEvent):Unit = {





  }
}