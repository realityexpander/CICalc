package com.realityexpander.cicalc.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class ExpressionEvaluatorTest {

    @Test
    fun `Simple expression is properly evaluated`() {
        // 1. ARRANGE
        val expression = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.PLUS),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.MINUS),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        val evaluator = ExpressionEvaluator(expression)

        // 2. ACT
        val result = evaluator.evaluate()

        // 3. ASSERT
        assertEquals(4.0, result, 0.0001)
        assertThat(result).isEqualTo(4.0)
    }

    @Test
    fun `Expression with parentheses is properly evaluated`() {
        // 1. ARRANGE
        val expression = listOf(
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.PLUS),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
            ExpressionPart.Op(Operation.MINUS),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        val evaluator = ExpressionEvaluator(expression)

        // 2. ACT
        val result = evaluator.evaluate()

        // 3. ASSERT
        assertEquals(4.0, result, 0.0001)
    }

    @Test
    fun `Expression with decimals is properly evaluated`() {
        // 1. ARRANGE
        val expression = listOf(
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(2.5),
            ExpressionPart.Op(Operation.PLUS),
            ExpressionPart.Number(4.5),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
            ExpressionPart.Op(Operation.MINUS),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        val evaluator = ExpressionEvaluator(expression)

        // 2. ACT
        val result = evaluator.evaluate()

        // 3. ASSERT
        assertEquals(3.0, result, 0.0001)
    }

    @Test
    fun `Expression with negative numbers is properly evaluated`() {
        // 1. ARRANGE
        val expression = listOf(
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Op(Operation.MINUS),
            ExpressionPart.Number(2.5),
            ExpressionPart.Op(Operation.PLUS),
            ExpressionPart.Number(4.5),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
            ExpressionPart.Op(Operation.MINUS),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        val evaluator = ExpressionEvaluator(expression)

        // 2. ACT
        val result = evaluator.evaluate()

        // 3. ASSERT
        assertEquals(-2.0, result, 0.0001)
    }

}