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
package com.szymikhackyourcareer.actors.example2

import com.szymik.hackyourcareer.actors.example2.Worker
import com.szymik.hackyourcareer.actors.example2.Worker.{Factorial, Result}
import com.szymikhackyourcareer.actors.BaseActorTest

/**
  * Set of tests for WorkerActor
  */
class WorkerActorTest extends BaseActorTest {

  "WorkerActor" should {

    "calculate factorial" in {
      // given
      val Number = 20L
      val ExpectedResult = 2432902008176640000L

      val workerActor = system.actorOf(Worker.props(1))

      // when
      workerActor ! Factorial(Number)

      // then
      expectMsg(Result(1, BigInt(ExpectedResult)))
    }
  }
}

