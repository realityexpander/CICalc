package com.realityexpander.cicalc.domain

enum class Operation(val symbol: Char) {
    PLUS('+'),
    MINUS('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%'),
}

val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char): Operation {
    return Operation.values().find {
        it.symbol == symbol
    }
        ?: throw IllegalArgumentException("Invalid symbol")
}