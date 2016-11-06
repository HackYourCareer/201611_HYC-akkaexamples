package com.szymikhackyourcareer.actors

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.testkit.{ImplicitSender, TestKit}
import com.typesafe.config.ConfigFactory
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.{Futures, ScalaFutures}
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{BeforeAndAfterAll, MustMatchers, OptionValues, WordSpecLike}

class BaseActorTest
  extends TestKit(ActorSystem("test-system", ConfigFactory.parseString( """akka.loggers = ["akka.testkit.TestEventListener"], akka.loglevel = "DEBUG"""")))
    with WordSpecLike
    with MustMatchers
    with BeforeAndAfterAll
    with MockFactory
    with ImplicitSender
    with Futures
    with ScalaFutures
    with OptionValues {

  implicit def defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(5, Seconds), interval = Span(200, Millis))

  override protected def afterAll() {
    super.afterAll()
    Http().shutdownAllConnectionPools().onComplete { _ =>
      system.terminate()
    }(system.dispatcher)
  }
}
