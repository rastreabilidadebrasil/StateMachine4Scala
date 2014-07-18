package com.rb.lib.statemachine

import com.rb.lib.statemachine.implicits._
import com.rb.lib.statemachine.model._
object Main extends App {
  val initial = InitialNode(1)
  val intermediario2 = RegularNode(2)
  val intermediario3 = RegularNode(3)
  val intermediario4 = RegularNode(4)
  val intermediario5 = RegularNode(5)
  val finalNode = FinalNode(6)

  val stateMachine = (initial goTo finalNode + intermediario2 + intermediario3) ~ (intermediario2 goTo intermediario3 + intermediario4) ~ (intermediario3 goTo intermediario4 + intermediario5) ~
    (intermediario4 goTo initial + intermediario3 + intermediario5) ~ (intermediario5 goTo initial + intermediario2 + intermediario4)

  println(stateMachine.getLongestPath(List(InitialNode(1), RegularNode(2), RegularNode(5))))
}