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
