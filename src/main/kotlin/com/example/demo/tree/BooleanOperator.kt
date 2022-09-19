package com.example.demo.tree

import java.util.stream.Collectors

class BooleanOperator(val operator: Operator, val children: MutableList<Node>) : Node {
    override fun isLeaf(): Boolean = false
    override fun toString(): String =
        if(operator==Operator.Not)
            if(children[0] is LeafNode)
                "${(children[0] as LeafNode).name}'"
            else
                "${children[0]}'"
        else
            children.stream().map {
                if(it.isLeaf())
                    it.toString()
                else if((it as BooleanOperator).operator==Operator.OR)
                    "($it)"
                else
                    "$it"
            }.collect(Collectors.joining(operator.symbol))
}