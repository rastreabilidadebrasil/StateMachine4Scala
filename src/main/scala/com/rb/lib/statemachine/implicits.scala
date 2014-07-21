package com.rb.lib.statemachine

import com.rb.lib.statemachine.model._
import com.rb.lib.statemachine.resolver._
object implicits {
  class NodeSetWrapper(val nodes: Set[Node]) {
    def +(node: NodeSetWrapper) = new NodeSetWrapper(nodes ++ node.nodes)
  }

  class StateMachineMapWrapper(val map: Map[Any, StateMachine]) {
    def ~(stateMachine: StateMachine): StateMachineMapWrapper = new StateMachineMapWrapper(map ++ Map(stateMachine.id -> stateMachine))
  }
  implicit def set2Wrapper(nodes: Set[Node]) = new NodeSetWrapper(nodes)
  implicit def wrapper2Set(set: NodeSetWrapper) = set.nodes
  implicit def node2NodeSetWrapper(node: Node) = new NodeSetWrapper(Set[Node](node))
  implicit def node2Set(node: Node) = Set[Node](node)
  implicit def map2MapWrapper(map: Map[Any, StateMachine]) = new StateMachineMapWrapper(map)
  implicit def wrapper2Map(wrapper: StateMachineMapWrapper) = wrapper.map
  implicit def stateMachine2MapWrapper(stateMachine: StateMachine) = new StateMachineMapWrapper(Map(stateMachine.id -> stateMachine))
  implicit def stateMachine2Map(stateMachine: StateMachine) = Map(stateMachine.id -> stateMachine)
  implicit def anySeq2NodeSeq(anyVals: Seq[Any]) = anyVals.map(anyVal => new Node() { val id = anyVal })

  implicit def compile(machine: StateMachineMapWrapper): StateMachineResolver = new StateMachineResolver(machine)
}
