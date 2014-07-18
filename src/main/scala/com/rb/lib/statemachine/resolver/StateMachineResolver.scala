package com.rb.lib.statemachine.resolver

import com.rb.lib.statemachine.implicits._
import com.rb.lib.statemachine.model.Node
import scala.Option.option2Iterable
import com.rb.lib.statemachine.model.StateMachine

class StateMachineResolver(val stateMachine: StateMachineMapWrapper) {

  def getAllPossiblePaths(nodes: Seq[Node]) = {
    val initial = stateMachine.map.flatMap {
      tuple => if (tuple._2.isInitial) Some(tuple._2) else None
    }.headOption.getOrElse(throw new Exception("Initial node not found"))
    getLastNodes(nodes, initial)
  }
  private def getLastNodes(stateSeq: Seq[Node], currentState: Node): Seq[(Seq[Node], Option[Node])] = {
    val nextStateSeq = stateSeq.diff(List(currentState))
    stateMachine.map.get(currentState.id).getOrElse(throw new Exception(s"Node not defined ${currentState.id}")).next match {
      case _ if stateSeq.length == nextStateSeq.length => List((nextStateSeq, None))
      case _ if nextStateSeq.isEmpty => List((Nil, Some(currentState)))
      case nextPossibleStates if nextStateSeq.exists(state => nextPossibleStates contains state) =>
        nextPossibleStates.flatMap { possibleState =>
          if (nextStateSeq.contains(possibleState)) {
            getLastNodes(nextStateSeq, possibleState)
          } else {
            List((nextStateSeq, Some(currentState)))
          }
        }.toList
      case _ => List((nextStateSeq, Some(currentState)))
    }
  }

  def orderByPathLength(stateSeq: Seq[Node], resultMaxSize: Int = 1, remaningPathLengthSortAsc: Boolean = true, finalStateFirst: Boolean = true): Seq[(Seq[Node], Option[Node])] = {
    getAllPossiblePaths(stateSeq).sortWith {
      (p1: (Seq[Node], Option[Node]), p2: (Seq[Node], Option[Node])) =>
        def sortByRemaningPath = {
          remaningPathLengthSortAsc match {
            case true => p1._1.length < p2._1.length
            case false => p1._1.length > p2._1.length
          }
        }

        finalStateFirst match {
          case true if isFinalNode(p1._2) && !isFinalNode(p2._2) => true
          case true if !isFinalNode(p1._2) && isFinalNode(p2._2) => false
          case _ => sortByRemaningPath
        }
    }.slice(0, resultMaxSize)
  }

  def getFurthestNode(stateSeq: Seq[Node]): (Seq[Node], Option[Node]) = {
    orderByPathLength(stateSeq).head
  }

  def getClosestNode(stateSeq: Seq[Node]): (Seq[Node], Option[Node]) = {
    orderByPathLength(stateSeq, 1, false).head
  }

  def isFinalNode(state: Option[Node]): Boolean = {
    state match {
      case Some(node) => node.isFinal
      case None => false
    }
  }

}