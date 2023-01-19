package com.realityexpander.cicalc.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ExpressionParserTest {

    @Test
    fun parse() {
        // 1. ARRANGE
        val parser = ExpressionParser("1+2")

        // 2. ACT
        val result = parser.parse()

        // 3. ASSERT
        assertEquals(3, result.size)
        assertEquals(ExpressionPart.Number(1.0), result[0])
        assertEquals(ExpressionPart.Op(Operation.ADD), result[1])
        assertEquals(ExpressionPart.Number(2.0), result[2])
    }

    @Test
    fun parseNumber() {
        val parser = ExpressionParser("1+2")
        val result = parser.parse()
        assertEquals(3, result.size)
        assertEquals(ExpressionPart.Number(1.0), result[0])
        assertEquals(ExpressionPart.Op(Operation.ADD), result[1])
        assertEquals(ExpressionPart.Number(2.0), result[2])
    }


    @Test
    fun parseParentheses() {
        val parser = ExpressionParser("(1+2)")
        val result = parser.parse()
        assertEquals(5, result.size)
        assertEquals(ExpressionPart.Parentheses(ParenthesesType.Opening), result[0])
        assertEquals(ExpressionPart.Number(1.0), result[1])
        assertEquals(ExpressionPart.Op(Operation.ADD), result[2])
        assertEquals(ExpressionPart.Number(2.0), result[3])
        assertEquals(ExpressionPart.Parentheses(ParenthesesType.Closing), result[4])
    }

    @Test
    fun `Simple expression is properly parsed`() {
        // 1. ARRANGE
        val parser = ExpressionParser("3+5-3x4/3")

        // 2. ACT
        val result = parser.parse()

        // 3. ASSERT
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        assertEquals(expected, result)
    }
}