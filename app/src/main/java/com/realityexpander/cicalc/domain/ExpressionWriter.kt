package com.realityexpander.cicalc.domain

class ExpressionWriter {

    var workExpression = ""

    fun processAction(action: CalculatorAction) {
        when(action) {
            CalculatorAction.Calculate -> {
                try {
                    val parser = ExpressionParser(prepareForCalculation())
                    val evaluator = ExpressionEvaluator(parser.parse())
                    workExpression = evaluator.evaluate().toString()
                } catch (e: Exception) {
                    workExpression = "Error"
                }
            }
            CalculatorAction.Clear -> {
                workExpression = ""
            }
            CalculatorAction.Decimal -> {
                if(canEnterDecimal()) {
                    workExpression += "."
                }
            }
            CalculatorAction.Delete -> {
                workExpression = workExpression.dropLast(1)
            }
            is CalculatorAction.Number -> {
                workExpression += action.number
            }
            is CalculatorAction.Op -> {
                if(canEnterOperation(action.operation)) {
                    workExpression += action.operation.symbol
                }
            }
            CalculatorAction.Parentheses -> {
                processParentheses()
            }
        }
    }

    private fun prepareForCalculation(): String {
        val newExpression = workExpression.dropLastWhile {
            it in "$operationSymbols(."
        }
        if(newExpression.isEmpty()) {
            return "0"
        }

        return newExpression
    }

    private fun processParentheses() {
        val openingCount = workExpression.count { it == '(' }
        val closingCount = workExpression.count { it == ')' }

        workExpression += when {
            // Check if the last character is valid for the start of a new expression
            workExpression.isEmpty() ||
                workExpression.last() in "$operationSymbols(" -> "("

            // Add a closing parentheses if the last character in the expression is a
            // digit or a closing parentheses and the number of opening parentheses
            // is equal to the number of closing parentheses
            workExpression.last() in "0123456789)" &&
                    openingCount == closingCount -> return

            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean {
        if(workExpression.isEmpty() || workExpression.last() in "$operationSymbols.()") {
            return false
        }

        return !workExpression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        if(operation in listOf(Operation.PLUS, Operation.MINUS)
            && workExpression.isEmpty()
        ) {
            return workExpression.isEmpty() || workExpression.last() in "$operationSymbols()0123456789"
        }

        return workExpression.isNotEmpty() || workExpression.last() in "0123456789)"
    }
}