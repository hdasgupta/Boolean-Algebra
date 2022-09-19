package com.example.demo

import com.example.demo.parse.Tokenizer
import com.example.demo.tree.NodeConverter
import com.example.demo.tree.TreeBuilder
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BooleanAlgebraApplicationTests {

	@Autowired
	lateinit var tokenizer: Tokenizer

	@Autowired
	lateinit var nodeConverter : NodeConverter

	@Autowired
	lateinit var treeBuilder : TreeBuilder

	@Test
	fun contextLoads() {
		println(treeBuilder.build(nodeConverter.convert(tokenizer.tokenize("A'B+AB'"))))
	}

}
