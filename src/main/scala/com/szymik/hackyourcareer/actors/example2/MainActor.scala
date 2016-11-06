package com.szymik.hackyourcareer.actors.example1

import akka.actor.{Actor, ActorLogging, PoisonPill, Props}
import com.szymik.hackyourcareer.actors.example1.HelloActor.{Done, SayHello}

/**
  * Main actor which is initializing sending greetings.
  */
class MainActor extends Actor with ActorLogging {

  override def preStart(): Unit = {
    val helloActor = context.actorOf(Props(new HelloActor()), "helloActor")

    helloActor ! SayHello("Hello there!")
  }

  override def receive: Receive = {

    case Done(message) ⇒
      log.info(message)

      self ! PoisonPill
  }

}
