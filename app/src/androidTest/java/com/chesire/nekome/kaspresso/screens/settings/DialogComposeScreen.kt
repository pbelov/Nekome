package com.chesire.nekome.kaspresso.screens.settings

import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen
import io.github.kakaocup.compose.node.element.ComposeScreen

/**
 * Base class for other Dialog robots to use.
 */
open class DialogComposeScreen<out T : ComposeScreen<T>> : BaseComposeScreen<T>() {

    open val root = getNodeWithTag(DialogTags.Root)
    val buttonOk = getNodeWithTag(DialogTags.OkButton)
    val buttonCancel = getNodeWithTag(DialogTags.CancelButton)

    /**
     * Confirms the dialog option.
     */
    fun confirm() = buttonOk.performClick()

    /**
     * Cancels the dialog option.
     */
    fun cancel() = buttonCancel.performClick()
}

/**
 * Base class for other Dialog results robots to use.
 */
abstract class DialogAsserts<out T : ComposeScreen<T>> : DialogComposeScreen<T>() {

    /**
     * Assert that the dialog is visible.
     */
    open fun isOnScreen() = root.assertIsDisplayed()

    /**
     * Assert that the dialog is not visible.
     */
    fun isNotOnScreen() = root.assertIsNotDisplayed()
}
