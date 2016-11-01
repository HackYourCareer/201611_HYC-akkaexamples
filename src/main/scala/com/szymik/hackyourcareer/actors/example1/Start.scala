package com.szymik.hackyourcareer.actors.example1

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}

/**
  * Main application entry point.
  */
object Start {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("PolitechnikaSlaska")

    val mainActor = system.actorOf(Props(new MainActor()), "mainActor")

    system.actorOf(Props(new Terminator(mainActor)), "terminator")
  }

  class Terminator(actor: ActorRef) extends Actor {
    context watch actor

    override def receive: Receive = {

      case Terminated(_) =>
        context.system.terminate()
    }
  }
}
