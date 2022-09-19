package com.example.demo.tree

import java.util.stream.Collectors

class BooleanOperator(val operator: Operator, val children: MutableList<Node>) : Node {
    override fun isLeaf(): Boolean = false
    override fun toString(): String = children.stream().map { if(it.isLeaf()) it.toString() else "($it)" }.collect(Collectors.joining(operator.symbol))
}