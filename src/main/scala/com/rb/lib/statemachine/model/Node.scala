package com.rb.lib.statemachine.model

trait Node[T] {
  val id: T
  val isInitial: Boolean = false
  val isFinal: Boolean = false
  override def toString = id.toString

  override def equals(obj: Any) = {
    obj match {
      case o: Node[T] => o.id == this.id
      case _ => false
    }
  }
  override def hashCode = this.id.hashCode
}