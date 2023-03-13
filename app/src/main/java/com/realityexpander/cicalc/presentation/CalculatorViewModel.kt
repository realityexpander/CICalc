package com.realityexpander.cicalc.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.realityexpander.cicalc.domain.CalculatorAction
import com.realityexpander.cicalc.domain.ExpressionWriter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val writer: ExpressionWriter = ExpressionWriter()
): ViewModel() {

    var expression by mutableStateOf("")
        private set

    val showFeedbackSharedFlow = MutableSharedFlow<Boolean>(0)

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        this.expression = writer.workExpression

        if(action is CalculatorAction.Clear) {
            viewModelScope.launch { showFeedbackSharedFlow.emit(true) }
        }
    }
}