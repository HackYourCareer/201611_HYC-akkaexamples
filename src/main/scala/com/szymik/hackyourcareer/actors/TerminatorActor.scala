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
