package com.rb.lib.statemachine.model

class RegularNode[T](override val id: T) extends NonFinalNode[T] {
}
object RegularNode {
  def apply[T](id: T) = new RegularNode[T](id)
}