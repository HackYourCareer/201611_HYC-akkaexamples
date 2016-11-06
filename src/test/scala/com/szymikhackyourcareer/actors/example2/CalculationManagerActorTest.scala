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

import com.szymik.hackyourcareer.actors.example2.CalculationManager
import com.szymik.hackyourcareer.actors.example2.CalculationManager.{Calculate, CalculationResult}
import com.szymikhackyourcareer.actors.BaseActorTest

class CalculationManagerActorTest extends BaseActorTest {

  "CalculationManagerActor" should {

    "trigger calculation" in {
      // given
      val calculationManager = system.actorOf(CalculationManager.props())

      // when
      calculationManager ! Calculate(1)

      // then
      expectMsg(CalculationResult(Map(0 → 1)))
    }

  }

}
