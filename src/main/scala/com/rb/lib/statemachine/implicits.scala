package com.rb.lib.statemachine

import com.rb.lib.statemachine.model._
import com.rb.lib.statemachine.resolver._
object implicits {
  class NodeSetWrapper[T](val nodes: Set[Node[T]]) {
    def +(node: NodeSetWrapper[T]) = new NodeSetWrapper[T](nodes ++ node.nodes)
  }

  class StateMachineMapWrapper[T](val map: Map[Any, StateMachine[T]]) {
    def ~(stateMachine: StateMachine[T]): StateMachineMapWrapper[T] = new StateMachineMapWrapper[T](map ++ Map(stateMachine.id -> stateMachine))
  }
  implicit def set2Wrapper[T](nodes: Set[Node[T]]) = new NodeSetWrapper(nodes)
  implicit def wrapper2Set[T](set: NodeSetWrapper[T]) = set.nodes
  implicit def node2NodeSetWrapper[T](node: Node[T]) = new NodeSetWrapper(Set[Node[T]](node))
  implicit def node2Set[T](node: Node[T]) = Set[Node[T]](node)
  implicit def map2MapWrapper[T](map: Map[Any, StateMachine[T]]) = new StateMachineMapWrapper(map)
  implicit def wrapper2Map[T](wrapper: StateMachineMapWrapper[T]) = wrapper.map
  implicit def stateMachine2MapWrapper[T](stateMachine: StateMachine[T]) = new StateMachineMapWrapper(Map(stateMachine.id -> stateMachine))
  implicit def stateMachine2Map[T](stateMachine: StateMachine[T]) = Map(stateMachine.id -> stateMachine)
  implicit def anySeq2NodeSeq[T](anyVals: Seq[T]) = anyVals.map(anyVal => new Node[T]() { val id = anyVal })

  implicit def compile[T](machine: StateMachineMapWrapper[T]): StateMachineResolver[T] = new StateMachineResolver(machine)
}
