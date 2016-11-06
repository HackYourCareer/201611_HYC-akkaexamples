package com.szymik.hackyourcareer.actors.example3

import akka.actor.{Actor, ActorLogging, Props}
import akka.pattern.pipe
import com.szymik.hackyourcareer.actors.example3.Worker.{Factorial, Result}

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
    * @param index identifier of worker actor and its result.
    * @param value of calculation.
    */
  case class Result(index: Int, value: Either[CalculationError, BigInt])

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
    case Factorial(number) if number == 10 ⇒
      log.info(s"Actor with index $index is simple stupid.")

    case Factorial(number) if number == 5 ⇒
      log.info(s"Actor with index $index do not want to calculate.")
      factorial(number).map(_ ⇒ Result(index, Left(CalculationError("Actor refused calculation for value 5")))) pipeTo sender()

    case Factorial(number) ⇒
      log.info(s"Actor with index $index is calculating result.")
      factorial(number).map(x ⇒ Result(index, Right(x))) pipeTo sender()
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
