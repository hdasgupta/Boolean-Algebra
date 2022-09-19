package com.example.demo.tree

import com.example.demo.tree.Operator.*
import org.springframework.stereotype.Service
import java.util.Stack

@Service
class TreeBuilder {
    fun build(nodes: List<Node>): Node {
        val operators: Stack<BooleanOperator> = Stack()
        val operands: Stack<Node> = Stack()
        var index = 0

        operators.push(BooleanOperator(Opening, mutableListOf()))

        while (index < nodes.size) {
            val node = nodes[index]
            if(node is LeafNode) {
                operands.push(node)
            } else if(node is BooleanOperator) {
                if(node.operator==Opening) {
                    operators.push(node)
                } else if(node.operator==Closing) {
                    autoProcess(operators, operands)
                } else if(node.operator==Not) {
                    val operand = operands.pop()
                    node.children.add(operand)
                    operands.push(node)
                } else {
                    if(operators.peek().operator.priority <= node.operator.priority) {
                        process(node.operator, operators, operands)
                    }
                    operators.push(node)
                }
            }
            index++
        }

        autoProcess(operators, operands)

        if(operands.size==1 && operators.isEmpty()) {
            return operands.pop()
        } else {
            throw RuntimeException("Error in Expression")
        }
    }

    private fun autoProcess(operators: Stack<BooleanOperator>, operands: Stack<Node>) {
        while(operators.peek().operator!=Opening) {
            processInternal(operators, operands)
        }
        operators.pop()
    }

    private fun process(operator: Operator, operators: Stack<BooleanOperator>, operands: Stack<Node>) {
        while(operators.peek().operator.priority <= operator.priority) {
            processInternal(operators, operands)
        }
    }

    private fun processInternal(operators: Stack<BooleanOperator>, operands: Stack<Node>) {
        val op2 = operands.pop()
        val op1 = operands.pop()
        val op = operators.pop()
        if(op1 is BooleanOperator) {
            if(op2 is BooleanOperator) {
                if(op1.operator==op.operator && op2.operator==op.operator) {
                    op.children.addAll(op1.children)
                    op.children.addAll(op2.children)
                } else if(op1.operator==op.operator) {
                    op.children.addAll(op1.children)
                    op.children.add(op2)
                } else if(op2.operator==op.operator) {
                    op.children.add(op1)
                    op.children.addAll(op2.children)
                } else {
                    op.children.add(op1)
                    op.children.add(op2)
                }
            } else if(op1.operator==op.operator) {
                op.children.addAll(op1.children)
                op.children.add(op2)
            } else {
                op.children.add(op1)
                op.children.add(op2)
            }
        } else if(op2 is BooleanOperator) {
            if(op2.operator==op.operator) {
                op.children.add(op1)
                op.children.addAll(op2.children)
            } else {
                op.children.add(op1)
                op.children.add(op2)
            }
        }

        operands.push(op)
    }
}