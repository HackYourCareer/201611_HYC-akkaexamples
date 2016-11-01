package com.szymik.hackyourcareer.actors.example1

import akka.actor.{Actor, ActorLogging}
import com.szymik.hackyourcareer.actors.example1.HelloActor.{Done, SayHello}

/**
  * This is HelloActor companion object with protocol for communicating with actor.
  */
object HelloActor {

  case class SayHello(message: String)

  case class Done(message: String)

}

/**
  * Actor which is saying hello.
  */
class HelloActor extends Actor with ActorLogging {

  override def receive: Receive = {

    case SayHello(message) ⇒
      log.info(message)

      sender() ! Done("Good job!")
  }

}
