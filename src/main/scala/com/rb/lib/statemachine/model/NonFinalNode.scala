package com.rb.lib.statemachine.model

trait NonFinalNode[T] extends Node[T] {
  def goTo(set: Set[Node[T]]) = {
    new StateMachine(id, isInitial, set)
  }

}
