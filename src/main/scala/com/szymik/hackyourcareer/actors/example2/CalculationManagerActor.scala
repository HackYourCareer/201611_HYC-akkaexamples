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
package com.szymik.hackyourcareer.actors.example2

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.szymik.hackyourcareer.actors.example2.CalculationManager.{Calculate, CalculationResult}
import com.szymik.hackyourcareer.actors.example2.Worker.{Factorial, Result}

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
  case class CalculationResult(results: Map[Int, BigInt])

  def props(): Props = Props(new CalculationManagerActor())

}

class CalculationManagerActor extends Actor with ActorLogging {

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

  def waitForResults(amount: Int, results: Map[Int, BigInt], replyTo: ActorRef): Receive = {

    case Result(index, value) ⇒
      val newResult = results + (index → value)

      if (newResult.size < amount) {
        context.become(waitForResults(amount, newResult, replyTo))
      }
      else {
        replyTo ! CalculationResult(newResult)
      }
  }

  private def startWorkers(howMuch: Int): Map[Int, ActorRef] = {
    (0 until howMuch).map(i ⇒ i → context.actorOf(Worker.props(i))) toMap
  }

  private def triggerCalculation(amount: Int, workers: Map[Int, ActorRef]) = {
    (0 until amount).foreach(i ⇒ workers(i) ! Factorial(i.toLong))
  }

}
