package com.example.demo.tree

import com.example.demo.parse.SubTag
import com.example.demo.parse.Tag
import org.springframework.stereotype.Service
import java.util.stream.IntStream

@Service
class NodeConverter {
    fun convert(nodes: List<com.example.demo.parse.Node>): List<Node> {
        var index:Int = 0
        val outNodes: MutableList<Node> = mutableListOf()
        while (index < nodes.size) {
            when(nodes[index].tag) {
                Tag.Variable -> {
                    //var andAdded:Boolean = false
                    if(index>0) {
                        if(nodes[index-1].subtag==SubTag.Not||nodes[index-1].subtag==SubTag.Closing||nodes[index-1].subtag==SubTag.Variable) {
                            outNodes.add(BooleanOperator(Operator.AND, mutableListOf()))
                            //andAdded = true
                        }
                    }
                    outNodes.add(LeafNode(nodes[index].symbol, false))
                    /*if(andAdded) {
                        index++
                    }*/
                }
                Tag.Bracket, Tag.Operator -> {
                    outNodes.add(BooleanOperator(
                        when(nodes[index].subtag) {
                            SubTag.And -> Operator.AND
                            SubTag.Or -> Operator.OR
                            SubTag.Not -> Operator.Not
                            SubTag.Opening -> Operator.Opening
                            else -> Operator.Closing
                        },
                        mutableListOf()
                    ))
                }
            }
            index++
        }
        return outNodes
    }
}