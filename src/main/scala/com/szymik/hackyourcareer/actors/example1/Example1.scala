package com.szymik.hackyourcareer.actors.example1

import akka.actor.{ActorSystem, Props}
import com.szymik.hackyourcareer.actors.TerminatorActor

/**
  * Main application entry point.
  */
object Example1 {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("PolitechnikaSlaska")
    val mainActor = system.actorOf(Props(new MainActor()), "mainActor")

    system.actorOf(Props(new TerminatorActor(mainActor)), "terminator")
  }
}
