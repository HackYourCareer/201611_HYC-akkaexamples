package com.szymik.hackyourcareer.actors.example3

import akka.actor.{ActorSystem, Props}
import com.szymik.hackyourcareer.actors.TerminatorActor

object Example3 {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("PolitechnikaSlaska")

    val mainActor = system.actorOf(MainActor.props(), "mainActor")
    system.actorOf(Props(new TerminatorActor(mainActor)), "terminator")
  }
}

