package com.realityexpander.cicalc.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ExpressionWriterTest {

    private lateinit var writer: ExpressionWriter

    @Before
    fun setUp() {
        writer = ExpressionWriter()
    }

    @Test
    fun `Initial parenthesis parsed`() {
        // 1. ARRANGE
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(1))
        writer.processAction(CalculatorAction.Op(Operation.PLUS))
        writer.processAction(CalculatorAction.Number(2))
        writer.processAction(CalculatorAction.Parentheses)

        // 2. ACT
        val result = writer.workExpression

        // 3. ASSERT
        assertThat(result).isEqualTo("(1+2)")
    }

    @Test
    fun `Closing parenthesis at the start NOT parsed`() {
        // 1. ARRANGE
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Parentheses)

        // 2. ACT
        val result = writer.workExpression

        // 3. ASSERT
        assertThat(result).isEqualTo("((")
    }

    @Test
    fun `Parenthesis around a number are parsed`() {
        // 1. ARRANGE
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(9))
        writer.processAction(CalculatorAction.Number(9))
        writer.processAction(CalculatorAction.Parentheses)

        // 2. ACT
        val result = writer.workExpression

        // 3. ASSERT
        assertThat(result).isEqualTo("(99)")
    }

    @Test
    fun `Initial parenthesis parsed with decimal`() {
        // 1. ARRANGE
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(1))
        writer.processAction(CalculatorAction.Op(Operation.PLUS))
        writer.processAction(CalculatorAction.Number(2))
        writer.processAction(CalculatorAction.Decimal)
        writer.processAction(CalculatorAction.Parentheses)

        // 2. ACT
        val result = writer.workExpression

        // 3. ASSERT
        assertThat(result).isEqualTo("(1+2.)")
    }
}