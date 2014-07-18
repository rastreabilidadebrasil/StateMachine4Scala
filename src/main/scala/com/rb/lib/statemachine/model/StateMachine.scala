package com.rb.lib.statemachine.model

class StateMachine(override val id: Any, override val isInitial: Boolean, val next: Set[Node]) extends Node {
}