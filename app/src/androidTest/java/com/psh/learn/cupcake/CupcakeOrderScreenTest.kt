package com.psh.learn.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.psh.learn.cupcake.data.DataSource
import com.psh.learn.cupcake.data.OrderUiState
import com.psh.learn.cupcake.ui.OrderSummaryScreen
import com.psh.learn.cupcake.ui.SelectOptionScreen
import com.psh.learn.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onQuantityButtonClicked = {}
            )
        }

        // assert all buttons are displayed
        DataSource.quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    @Test
    fun selectOptionScreen_verifyContent() {
        // content for the options screen
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        // launch SelectOptionScreen composable directly without navigation
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // assert all flavours appear on the screen
        flavors.forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }

        // assert subtotal is displayed
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

        // assert Next button is disabled since no option is selected
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOptionScreen_optionSelection_nextButtonEnabled() {
        // content for the options screen
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        // launch SelectOptionScreen composable directly without navigation
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // assert all flavours appear on the screen
        flavors.forEach {
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }

        // assert subtotal is displayed
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

        // Select an option
        composeTestRule.onNodeWithText("Vanilla").performClick()

        // assert Next button is enabled since an option is selected
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    @Test
    fun summaryScreen_verifyContent() {
        val fakeOrderUiState = OrderUiState(2, "Vanilla", "Fri Nov 24", "$100", listOf())
        composeTestRule.setContent {
            OrderSummaryScreen(orderUiState = fakeOrderUiState)
        }
        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, fakeOrderUiState.price)
        ).assertIsDisplayed()
    }

}