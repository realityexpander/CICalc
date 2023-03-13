package com.realityexpander.cicalc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <T> Flow<T>.CollectAsEffect(
    context: CoroutineContext = EmptyCoroutineContext,
    block: (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEach(block).flowOn(context).launchIn(this)
    }
}

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel(),
    onShowFeedbackDialog: () -> Unit = {}
) {

//    LaunchedEffect(true) {
//        viewModel.showFeedbackSharedFlow.collect { shouldShow ->
//            if (shouldShow) {
//                onShowFeedbackDialog()
//            }
//        }
//    }
//    viewModel.showFeedbackSharedFlow.CollectAsEffect { shouldShow ->
//        if (shouldShow) {
//            onShowFeedbackDialog()
//        }
//    }
//    viewModel.showFeedbackSharedFlow.collectAsState(initial = false).value.let { shouldShow ->
//        if (shouldShow) {
//            onShowFeedbackDialog()
//        }
//    }
    LaunchedEffect(true) {
        viewModel.showFeedbackSharedFlow.collectLatest { shouldShow ->
            if (shouldShow) {
                onShowFeedbackDialog()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CalculatorDisplay(
                expression = viewModel.expression,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // display uses leftover space from the button grid
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(
                        vertical = 64.dp,
                        horizontal = 16.dp
                    )
            )
            Spacer(modifier = Modifier.height(8.dp))

            CalculatorButtonGrid(
                actions = calculatorActions,
                onAction = viewModel::onAction,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}