package com.whereabouts

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class WhereAboutsServiceActor extends Actor with WhereAboutsService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


trait WhereAboutsService extends HttpService {

  val inventoryList = List("item1", "item2","item3")

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
  path("show"){
    get {
      respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
        complete {
          <html>
            <body>
              {for (item <- inventoryList) yield <h3>{item}</h3> }
            </body>
          </html>
        }
      }
    }
  }
}