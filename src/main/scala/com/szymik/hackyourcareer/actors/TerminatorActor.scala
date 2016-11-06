package com.szymik.hackyourcareer.actors

import akka.actor.{Actor, ActorRef, Terminated}

/**
  * Terminator actor which is terminating whole Actor System.
  *
  * @param actor which is observed as parent.
  */
class TerminatorActor(actor: ActorRef) extends Actor {
  context watch actor

  override def receive: Receive = {

    case Terminated(_) =>
      context.system.terminate()
  }
}
