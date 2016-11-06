package com.szymik.hackyourcareer.actors.example3

import akka.actor.{Actor, ActorLogging, PoisonPill, Props}
import com.szymik.hackyourcareer.actors.example3.CalculationManager.{Calculate, CalculationResult}

object MainActor {

  /**
    * Returns props for main actor.
    *
    * @return
    */
  def props(): Props = Props(new MainActor())
}

/**
  * Main actor which is initializing sending greetings.
  */
class MainActor extends Actor with ActorLogging {

  val HowManyCalculation = 20

  override def preStart(): Unit = {
    val calculationManager = context.actorOf(CalculationManager.props(), "calculationManagerActor")
    calculationManager ! Calculate(HowManyCalculation)
  }

  override def receive: Receive = {

    case CalculationResult(results) ⇒
      val resultsMessage = buildResponseWithValues(results)
      log.info(resultsMessage)

      self ! PoisonPill
  }

  private def buildResponseWithValues(results: Map[Int, Either[CalculationError, BigInt]]): String = {
    results.toSeq.sortBy(_._1).map {
      case (index, Right(value)) ⇒
        s"\nCalculation number $index returned $value"
      case (index, Left(error)) ⇒
        s"\nCalculation number $index failed with error: ${error.message}"
    }.mkString(", ")
  }

}
