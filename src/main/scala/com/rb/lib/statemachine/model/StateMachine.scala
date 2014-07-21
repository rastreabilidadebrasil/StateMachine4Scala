package com.rb.lib.statemachine.model

class StateMachine[T](override val id: T, override val isInitial: Boolean, val next: Set[Node[T]]) extends Node[T] {
  override def toString = id.toString
}
object StateMachine {
  def apply[T](id: T, isInitial: Boolean, next: Set[Node[T]]) = new StateMachine[T](id, isInitial, next)
  def defaultFinalNode[T](id: T) = new StateMachine(id, false, Set[Node[T]]())
}