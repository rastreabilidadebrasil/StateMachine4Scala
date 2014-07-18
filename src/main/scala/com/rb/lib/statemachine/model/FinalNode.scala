package com.rb.lib.statemachine.model

import com.rb.lib.statemachine.implicits._
class FinalNode(override val id: Any) extends StateMachine(id, false, Set()) {
  override val isFinal = true
  def +(node: NodeSetWrapper) = new NodeSetWrapper(node.nodes ++ Set(this))
}
object FinalNode {
  def apply(id: Int) = new FinalNode(id)
}