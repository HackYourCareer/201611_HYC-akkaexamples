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

import akka.actor.{Actor, ActorLogging, Props}
import akka.pattern.pipe
import com.szymik.hackyourcareer.actors.example2.Worker.{Factorial, Result}

import scala.annotation.tailrec
import scala.concurrent.Future

/**
  * Protocol for communicating with WorkerActor.
  *
  */
object Worker {

  /**
    * Calculate factorial.
    *
    * @param number - number which will be calculated.
    */
  case class Factorial(number: Long)

  /**
    * Result of calculation.
    *
    * @param index  identifier of worker actor and its result.
    * @param value of calculation.
    */
  case class Result(index: Int, value: BigInt)

  /**
    * Props for creating actor.
    *
    * @return props for actor.
    */
  def props(index: Int): Props = Props(new WorkerActor(index))
}

/**
  * Actor which is performing really complicated calculation, really complicated.
  */
class WorkerActor(index: Int) extends Actor with ActorLogging {

  import context.dispatcher

  override def receive: Receive = {
    case Factorial(number) ⇒
      log.info(s"Actor with index $index is calculating result.")
      factorial(number).map(Result(index, _)) pipeTo sender()
  }

  /**
    * This code is not yet future ready and we do not want to show too much in first iteration!
    *
    * TODO as homework: add proper dispatcher!
    */
  def factorial(n: Long): Future[BigInt] = {

    @tailrec
    def factorialAccumulator(acc: BigInt, n: Long): BigInt = {
      n match {
        case 0 ⇒ acc
        case _ ⇒ factorialAccumulator(BigInt(n) * acc, n - 1)
      }
    }

    Future {
      factorialAccumulator(1, n)
    }
  }
}
