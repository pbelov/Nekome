package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.core.compose.composables.DialogTags
import io.github.kakaocup.compose.node.element.ComposeScreen

/**
 * Base class for other Dialog robots to use.
 */
abstract class DialogComposeScreen<out T : ComposeScreen<T>> : BaseComposeScreen<T>() {

    /**
     * Confirms the dialog option.
     */
    fun confirm() = clickOnNodeWithTag(DialogTags.OkButton)

    /**
     * Cancels the dialog option.
     */
    fun cancel() = clickOnNodeWithTag(DialogTags.CancelButton)
}

/**
 * Base class for other Dialog results robots to use.
 */
abstract class DialogAsserts : BaseComposeScreen<DialogAsserts>() {

    /**
     * Assert that the dialog is visible.
     */
    open fun isOnScreen() = isOnScreen(DialogTags.Root)

    /**
     * Assert that the dialog is not visible.
     */
    fun isNotOnScreen() = isNotOnScreen(DialogTags.Root)
}
