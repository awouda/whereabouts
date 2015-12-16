package com.whereabouts

import akka.actor.{Props, Actor}
import akka.util.Timeout
import com.whereabouts.messages.{StoreEvent, StoreEventMsg}
import com.whereabouts.services.actor.StoreEventsActor
import spray.routing._
import spray.http._
import MediaTypes._
import scala.concurrent.duration._
import akka.pattern.ask
import com.whereabouts.marshaller.Json4sProtocol._


class WhereAboutsServiceActor extends Actor with WhereAboutsService {

  def actorRefFactory = context

  def receive = runRoute(myRoute)
}


case class Person(age:Int, name:String)
case class PersonMap(persons:Map[String,List[Person]])


trait WhereAboutsService extends HttpService {

  implicit def executionContext = actorRefFactory.dispatcher

  implicit val timeout = Timeout(5 seconds)


  val inventoryList = List("item1", "item2", "item3")

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
             inventoryList

           }
        }
      }
    } ~
      path("show") {
        get {
            complete {
              val storeEventsActor = actorRefFactory.actorOf(Props(new StoreEventsActor),"nr-"+System.nanoTime())
              val future = storeEventsActor ? StoreEventMsg( StoreEvent(new java.util.Date().toString))
              future.mapTo[String]
            }
          }
      } ~
      path("test") {
        get {
          complete {
            actorRefFactory.actorOf(Props(new StoreEventsActor))
              .ask(StoreEventMsg(  StoreEvent(new java.util.Date().toString)))
              .mapTo[String]
          }
        }
      } ~
      path("update")  {
        post {

          entity(as[StoreEvent]) { storeEvent =>

            complete(s"Event: ${storeEvent.data}")
          }
        }
      }
}