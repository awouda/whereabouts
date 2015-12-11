package com.whereabouts

import akka.actor.{Props, ActorLogging, Actor}
import akka.event.LoggingAdapter
import akka.util.Timeout
import com.whereabouts.messages.{StoreEvent, StoreEventMsg}
import com.whereabouts.services.actor.StoreEventsActor
import org.slf4j.Logger
import spray.routing._
import spray.http._
import MediaTypes._
import spray.util.SprayActorLogging
import scala.concurrent.duration._
import akka.pattern.ask

class WhereAboutsServiceActor extends Actor with WhereAboutsService {

  def actorRefFactory = context

  def receive = runRoute(myRoute)
}

trait WhereAboutsService extends HttpService {




  implicit def executionContext = actorRefFactory.dispatcher

  implicit val timeout = Timeout(5 seconds)


  val inventoryList = List("item1", "item2", "item3")

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Whereabouts API</h1>
              </body>
            </html>
          }
        }
      }
    } ~
      path("show") {
        get {
          respondWithMediaType(`text/html`) {
            complete {
              val storeEventsActor = actorRefFactory.actorOf(Props(new StoreEventsActor))
              val future = storeEventsActor ? StoreEventMsg(new StoreEvent(new java.util.Date().toString))
              future.mapTo[String]
            }
          }
        }
      } ~
      path("test") {
        get {
          complete {
            actorRefFactory.actorOf(Props(new StoreEventsActor))
              .ask(StoreEventMsg(new StoreEvent(new java.util.Date().toString)))
              .mapTo[String]
          }
        }
      }
}