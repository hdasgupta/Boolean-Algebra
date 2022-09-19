package com.example.demo.tree

class LeafNode(val name: String, val not: Boolean) : Node {
    override fun isLeaf() = true
    override fun toString(): String = name + if(not) "'" else ""
}
