package com.example.demo.tree

class LeafNode(val name: String, var not: Boolean) : Node {
    override fun isLeaf() = true
    override fun toString(): String = name + if(not) "'" else ""
}
