package com.rb.lib.statemachine.model

class FinalNode(override val id: Any) extends Node {
  override val isFinal = true
}
object FinalNode {
  def apply(id: Int) = new FinalNode(id)
}