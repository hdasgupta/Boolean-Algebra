package com.example.demo.tree

enum class Operator(val symbol: String, val priority: Int) {
    AND("", 0),
    NAND("", 0),
    XOR("", 1),
    OR("+", 2),
    NOR("+", 2),
    Not("'", Int.MAX_VALUE),
    Opening("(", Int.MAX_VALUE),
    Closing(")", Int.MAX_VALUE)
}