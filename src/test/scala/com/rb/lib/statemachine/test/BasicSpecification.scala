package com.rb.lib.statemachine.test

import org.specs2.mutable._
import com.rb.lib.statemachine.model.InitialNode
import com.rb.lib.statemachine.model.RegularNode
import com.rb.lib.statemachine.implicits._
import com.rb.lib.statemachine.model.FinalNode
import com.rb.lib.statemachine.resolver.StateMachineResolver

class BasicSpecification extends Specification {
  val initial = InitialNode(0)
  val regular1 = RegularNode(1)
  val regular2 = RegularNode(2)
  val regular3 = RegularNode(3)
  val finalNode = FinalNode(4)

  val basicStateMachine = (initial goTo regular1 + regular2) ~ (regular1 goTo regular2 + regular3) ~ (regular2 goTo regular3 + regular1) ~ (regular3 goTo finalNode) ~ finalNode

  def basicFlowForFurthest = {

    "The basic state machine on getFurthestNode" should {
      "0 -> 1 -> 3 -> 4 stop on 4 and consume all input nodes" in {
        val longestPath = basicStateMachine.getFurthestNode(List(initial, regular1, regular3, finalNode))
        longestPath._2.get.id == 4 && longestPath._1.isEmpty
      }
      "0 -> 1 -> 2 -> 1 -> 2 -> 1 -> 2 -> 3 -> 4 stop on 4 and consume all input nodes" in {
        val longestPath = basicStateMachine.getFurthestNode(List(initial, regular1, regular2, regular1, regular2, regular1, regular2, regular3, finalNode))
        longestPath._2.get.id == 4 && longestPath._1.isEmpty
      }
    }
  }
  def basicFlowForClosest = {
    "The basic state machine on getClosestNode" should {
      "0 -> 1 -> 3 -> 4 stop on 4 and consume all input nodes" in {
        val shortestPath = basicStateMachine.getClosestNode(List(initial, regular1, regular3, finalNode))
        shortestPath._2.get.id == 4 && shortestPath._1.isEmpty
      }
      "0 -> 1 -> 2 -> 1 -> 2 -> 1 -> 2 -> 3 -> 4 stop on 4 and dont consume all input nodes" in {
        val shortestPath = basicStateMachine.getClosestNode(List(initial, regular1, regular2, regular1, regular2, regular1, regular2, regular3, finalNode))
        shortestPath._2.get.id == 4 && shortestPath._1.length == 5
      }
    }
  }

  basicFlowForFurthest
  basicFlowForClosest
}

