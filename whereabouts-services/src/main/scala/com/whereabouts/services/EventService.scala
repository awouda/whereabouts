package com.whereabouts.services

import akka.event.slf4j.SLF4JLogging
import com.whereabouts.messages.StoreEvent

trait EventService {

  def store(se:StoreEvent)

}

class FakeEventService extends EventService  with SLF4JLogging {

  def store(se:StoreEvent):Unit = {

    log.info(s"Yeah logging stuff ${se} ")





  }
}