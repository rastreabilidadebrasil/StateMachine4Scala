package com.rb.lib.statemachine.model

import com.rb.lib.statemachine.implicits._
class FinalNode[T](override val id: T) extends StateMachine(id, false, Set[Node[T]]()) {
  override val isFinal = true
  def +(node: NodeSetWrapper[T]) = new NodeSetWrapper[T](node.nodes ++ Set[Node[T]](this))
}
object FinalNode {
  def apply[T](id: T) = new FinalNode[T](id)
}