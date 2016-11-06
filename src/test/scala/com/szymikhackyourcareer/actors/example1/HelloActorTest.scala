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
package com.szymikhackyourcareer.actors.example1

import akka.actor.Props
import com.szymik.hackyourcareer.actors.example1.HelloActor
import com.szymik.hackyourcareer.actors.example1.HelloActor.{Done, SayHello}
import com.szymikhackyourcareer.actors.BaseActorTest

/**
  * Test prepared for HelloActor.
  */
class HelloActorTest extends BaseActorTest {

  "HelloActor" should {

    "respond to hello message" in {
      // given
      val helloActor = system.actorOf(Props(new HelloActor()))

      // when
      helloActor ! SayHello("test message")

      // then
      expectMsgPF() {

        case Done(message) ⇒
          message mustBe "Good job!"
      }
    }
  }



}
