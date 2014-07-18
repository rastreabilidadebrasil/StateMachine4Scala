package com.rb.lib.statemachine.model

import scala.collection.mutable.MutableList

class InitialNode(override val id: Any) extends NonFinalNode {
  override val isInitial = true
}
object InitialNode {
  def apply(id: Int) = new InitialNode(id)
}