package com.rb.lib.statemachine.model

trait NonFinalNode extends Node {
  def goTo(set: Set[Node]) = {
    new StateMachine(id, isInitial, set)
  }

}
