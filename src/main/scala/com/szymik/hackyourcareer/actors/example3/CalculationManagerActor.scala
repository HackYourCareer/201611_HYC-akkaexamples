package com.szymik.hackyourcareer.actors.example3

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.szymik.hackyourcareer.actors.example3.CalculationManager.{Calculate, CalculationResult}
import com.szymik.hackyourcareer.actors.example3.Worker.{Factorial, Result}

import scala.concurrent.duration._

object CalculationManager {

  /**
    * Case class which is requesting calculation.
    *
    * @param amount how many calculation should be performed.
    */
  case class Calculate(amount: Int)

  /**
    * Case class representing calculation result.
    *
    * @param results of calculation.
    */
  case class CalculationResult(results: Map[Int, Either[CalculationError, BigInt]])

  def props(): Props = Props(new CalculationManagerActor())

}

class CalculationManagerActor extends Actor with ActorLogging {

  case object Timeout

  import context.dispatcher

  override def preStart(): Unit = {
    context.system.scheduler.scheduleOnce(50 milliseconds, self, Timeout)
  }

  override def receive: Receive = {

    case Calculate(amount) ⇒
      val savedSender = sender()

      if (amount > 0) {
        val workers = startWorkers(amount)
        triggerCalculation(amount, workers)

        context.become(waitForResults(amount, Map(), savedSender))
      }
      else {
        savedSender ! CalculationResult(Map())
      }
  }

  def waitForResults(amount: Int, results: Map[Int, Either[CalculationError, BigInt]], replyTo: ActorRef): Receive = {

    case Result(index, value) ⇒
      val newResult = results + (index → value)

      if (newResult.size < amount) {
        context.become(waitForResults(amount, newResult, replyTo))
      }
      else {
        replyTo ! CalculationResult(newResult)
      }

    case Timeout ⇒
      val missingResults: Map[Int, Either[CalculationError, BigInt]] = (0 until amount).filterNot(results.contains)
        .map(i ⇒ i -> Left(CalculationError("Calculation has been timed out."))).toMap

      replyTo ! CalculationResult(missingResults ++ results)
  }

  private def startWorkers(howMuch: Int): Map[Int, ActorRef] = {
    (0 until howMuch).map(i ⇒ i → context.actorOf(Worker.props(i))) toMap
  }

  private def triggerCalculation(amount: Int, workers: Map[Int, ActorRef]) = {
    (0 until amount).foreach(i ⇒ workers(i) ! Factorial(i.toLong))
  }

}
