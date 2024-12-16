package com.chesire.nekome.kaspresso.screens.base

import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performClick
import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.kaspresso.getResource
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

/**
 * Screen with basic asserts
 */
open class BaseComposeScreen<out T : ComposeScreen<T>> :
    ComposeScreen<T>(semanticsProvider = composeTestRule) {

    protected fun clickOnNodeWithText(text: String) {
        getNodeWithText(text).performClick()
    }

    protected fun clickOnNodeWithTag(tag: String) {
        getNodeWithTag(tag).performClick()
    }

    protected fun clickOnLastNode(stringId: Int) {
        getAllNodesWithText(stringId.getResource())
            .onLast()
            .performClick()
    }

    protected fun getNodeWithText(text: String): KNode {
        return child {
            hasText(text)
        }
    }

    protected fun getAllNodesWithText(text: String): SemanticsNodeInteractionCollection {
        return composeTestRule.onAllNodesWithText(text)
    }

    protected fun getAllNodesWithTag(tag: String, useUnmergedTree: Boolean = false): SemanticsNodeInteractionCollection {
        return composeTestRule.onAllNodesWithTag(tag, useUnmergedTree)
    }

    protected fun getNodeWithTag(tag: String): KNode {
        return child {
            hasTestTag(tag)
        }
    }

    // TODO: check this
    fun isSelected(tag: String) = getNodeWithTag(tag).assertIsSelected()

    fun isOnScreen(tag: String) = getNodeWithTag(tag).assertIsDisplayed()

    fun isNotOnScreen(tag: String) = getNodeWithTag(tag).assertIsNotDisplayed()

    fun checkSnackBarErrorText(
        text: String,
        substring: Boolean = true,
        ignoreCase: Boolean = true
    ) {
        getNodeWithTag(CredentialsTags.Snackbar)
            .child<KNode> {
                hasAnyChild(
                    androidx.compose.ui.test.hasText(
                        text, ignoreCase = substring, substring = ignoreCase
                    )
                )
            }
            .assertIsDisplayed()
    }

    companion object {
        private lateinit var composeTestRule: ComposeContentTestRule

        fun getRule(): ComposeContentTestRule {
            return composeTestRule
        }

        fun setRule(composeTestRule: ComposeContentTestRule) {
            Companion.composeTestRule = composeTestRule
        }
    }
}

