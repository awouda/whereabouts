package com.whereabouts.services

import com.whereabouts.messages.StoreEvent

trait EventService {

  def store(se:StoreEvent)

}

class FakeEventService extends EventService{

  //Logger logger = LoggerFactory.getLogger(HelloWorld.class);


  def store(se:StoreEvent):Unit = {


    println(" storing "+se)





  }
}