/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
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
