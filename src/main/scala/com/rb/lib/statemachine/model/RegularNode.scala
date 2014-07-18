package com.rb.lib.statemachine.model

class RegularNode(override val id: Any) extends NonFinalNode {
}
object RegularNode {
  def apply(id: Int) = new RegularNode(id)
}