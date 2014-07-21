package com.rb.lib.statemachine.model

import scala.collection.mutable.MutableList

class InitialNode[T](override val id: T) extends NonFinalNode[T] {
  override val isInitial = true
}
object InitialNode {
  def apply[T](id: T) = new InitialNode[T](id)
}