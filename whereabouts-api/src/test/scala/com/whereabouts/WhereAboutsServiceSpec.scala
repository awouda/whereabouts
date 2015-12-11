package com.whereabouts

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class WhereAboutsServiceSpec extends Specification with Specs2RouteTest with WhereAboutsService {
  def actorRefFactory = system
  
  "WhereAboutService" should {

    "return a API response for GET requests to the root path" in {
      Get() ~> myRoute ~> check {
        responseAs[String] must contain("API")
      }
    }

    "has a GET request for path show" in {
      Get("/show") ~> myRoute ~> check {
        handled must beTrue
      }
    }
  }
}
