package com.realityexpander.cicalc.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.realityexpander.cicalc.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTest {

    @get:Rule
    val composeTestRule =
        createAndroidComposeRule(MainActivity::class.java)

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test // what we do, what we expect
    fun enterExpression_correctResultDisplayed() {

        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText("1").performClick()

        composeTestRule.onNodeWithText("+").assertExists()
        composeTestRule.onNodeWithText("+").performClick()

        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("2").performClick()

        composeTestRule.onNodeWithText("x").assertExists()
        composeTestRule.onNodeWithText("x").performClick()

        composeTestRule.onNodeWithText("3").assertExists()
        composeTestRule.onNodeWithText("3").performClick()

        composeTestRule.onNodeWithText("=").assertExists()
        composeTestRule.onNodeWithText("=").performClick()

        composeTestRule.onNodeWithText("7").assertIsDisplayed()
    }
}