package com.chesire.nekome.kaspresso.screens

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

/**
 * Screen with basic asserts
 */
open class BaseComposeScreen<out T : ComposeScreen<T>> :
    ComposeScreen<T>(semanticsProvider = composeTestRule) {

    protected fun getNodeWithText(text: String): KNode {
        return child {
            hasText(text)
        }
    }

    protected fun getNodeWithTag(tag: String): KNode {
        return child {
            hasTestTag(tag)
        }
    }

    fun isOnScreen(tag: String) = getNodeWithTag(tag).assertIsSelected()

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

        fun setRule(composeTestRule: ComposeContentTestRule) {
            this.composeTestRule = composeTestRule
        }
    }
}

