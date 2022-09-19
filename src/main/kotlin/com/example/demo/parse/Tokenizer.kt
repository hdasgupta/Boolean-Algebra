package com.example.demo.parse

class Tokenizer {
    fun tokenize(input: String): List<Node?> = input.chars().mapToObj { it as Char }.map {
        if((it>='a' && it<='z') || (it>='A' && it<='Z'))
            Node(it.toString(), Tag.Variable, SubTag.Variable)
        else if(it=='\''||it=='+'||it=='.')
            Node(it.toString(), Tag.Operator,
                when(it) {
                    '\''->SubTag.Not
                    '+' ->SubTag.Or
                    '.'->SubTag.And
                    else -> null
                })
        else if(it=='(')
            Node(it.toString(), Tag.Operator, SubTag.Opening)
        else if(it==')')
            Node(it.toString(), Tag.Operator, SubTag.Closing)
        else null
    }.toList()
}