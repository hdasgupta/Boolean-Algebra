package com.example.demo.parse

import org.springframework.stereotype.Service

@Service
class Tokenizer {
    fun tokenize(input: String): List<Node> = input.chars().mapToObj { it.toChar() }.map {
        if((it>='a' && it<='z') || (it>='A' && it<='Z'))
            Node(it.toString(), Tag.Variable, SubTag.Variable)
        else if(it=='\''||it=='+'||it=='.')
            Node(it.toString(), Tag.Operator,
                when(it) {
                    '\''->SubTag.Not
                    '+' ->SubTag.Or
                    '.'->SubTag.And
                    else -> throw RuntimeException("Unknown operator")
                })
        else if(it=='(')
            Node(it.toString(), Tag.Operator, SubTag.Opening)
        else if(it==')')
            Node(it.toString(), Tag.Operator, SubTag.Closing)
        else throw RuntimeException("Unknown operator")
    }.toList()
}