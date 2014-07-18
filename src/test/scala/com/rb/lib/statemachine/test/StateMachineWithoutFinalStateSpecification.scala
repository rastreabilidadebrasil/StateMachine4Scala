package com.rb.lib.statemachine.test

import org.specs2.mutable._
import com.rb.lib.statemachine.model.InitialNode
import com.rb.lib.statemachine.model.RegularNode
import com.rb.lib.statemachine.implicits._
import com.rb.lib.statemachine.model.FinalNode

class StateMachineWithoutFinalStateSpecification extends Specification {
  val initial = InitialNode(0)
  val regular1 = RegularNode(1)
  val regular2 = RegularNode(2)
  val regular3 = RegularNode(3)

  val stateMachineWithoutFinalState = (initial goTo regular1 + regular2) ~ (regular1 goTo regular2 + regular3 + regular1) ~ (regular2 goTo regular3 + regular1) ~ (regular3 goTo initial + regular3)

  def stateMachineWithoutFinalStateFlowForFurthest = {

    "The state machine without final state on getFurthestNode" should {
      "1  stop on empty node and dont consume any input" in {
        val longestPath = stateMachineWithoutFinalState.getFurthestNode(List(regular1))
        longestPath._2 == None && longestPath._1.length == 1
      }
      "0 stop on initial node and consume all input" in {
        val longestPath = stateMachineWithoutFinalState.getFurthestNode(List(initial))
        longestPath._2.get == initial && longestPath._1.length == 0
      }
      "0 -> 1 -> 1 -> 1 -> 1  stop on 1 and consume all input" in {
        val longestPath = stateMachineWithoutFinalState.getFurthestNode(List(initial, regular1, regular1, regular1, regular1))
        longestPath._2.get == regular1 && longestPath._1.length == 0
      }
      "0 -> 1 -> 1 -> 1 -> 1 -> 2 -> 1 -> 2 -> 3 ->3 ->3 stop on 3 consume all input" in {
        val longestPath = stateMachineWithoutFinalState.getFurthestNode(List(initial, regular1, regular1, regular1, regular1, regular2, regular1, regular2, regular3, regular3, regular3))
        longestPath._2.get == RegularNode(3) && longestPath._1.length == 0
      }
      "0 -> 1 -> 1 -> 1 -> 1 -> 2 -> 1 -> 3 -> 0 stop on initial and consume all input" in {
        val longestPath = stateMachineWithoutFinalState.getFurthestNode(List(initial, regular1, regular1, regular1, regular1, regular2, regular1, regular2, regular3, initial))
        println(longestPath)
        longestPath._1.length == 0
      }
    }
  }
  def stateMachineWithoutFinalStateFlowForClosest = {

    "The state machine without final state on getClosestNode" should {
      "1 stop on empty node and dont consume any input" in {
        val shortestPath = stateMachineWithoutFinalState.getClosestNode(List(regular1))
        shortestPath._2 == None && shortestPath._1.length == 1
      }
      "0  stop on initial node and consume all input" in {
        val shortestPath = stateMachineWithoutFinalState.getClosestNode(List(initial))
        shortestPath._2.get == initial && shortestPath._1.length == 0
      }
      "0 ->1  stop on initial node and consume only initial" in {
        val shortestPath = stateMachineWithoutFinalState.getClosestNode(List(initial, regular1))
        shortestPath._2.get == initial && shortestPath._1.length == 1
      }
    }
  }

  stateMachineWithoutFinalStateFlowForFurthest
  stateMachineWithoutFinalStateFlowForClosest
}

