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
