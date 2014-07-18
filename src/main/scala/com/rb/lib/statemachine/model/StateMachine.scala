package com.rb.lib.statemachine.model

class StateMachine(override val id: Any, override val isInitial: Boolean, val next: Set[Node]) extends Node {
  override def toString = id.toString
}
object StateMachine {
  def apply(id: Any, isInitial: Boolean, next: Set[Node]) = new StateMachine(id, isInitial, next)
  def defaultFinalNode(id: Any) = new StateMachine(id, false, Set())
}