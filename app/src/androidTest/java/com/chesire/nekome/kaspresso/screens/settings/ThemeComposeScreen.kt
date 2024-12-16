package com.chesire.nekome.kaspresso.screens.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onAllNodesWithTag
import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.core.preferences.flags.Theme
import com.chesire.nekome.helpers.getResource

/**
 * Robot to interact with the theme dialog.
 */
class ThemeComposeScreen : DialogComposeScreen<ThemeComposeScreen>() {

    /**
     * Open the theme dialog, and picks the "System default" option.
     */
    fun chooseSystem() {

        clickOnLastNode(Theme.System.stringId)
    }

    /**
     * Open the theme dialog, and picks the "Dark" option.
     */
    fun chooseDark() {
        clickOnLastNode(Theme.Dark.stringId)
    }

    /**
     * Open the theme dialog, and picks the "Light" option.
     */
    fun chooseLight() {
        clickOnLastNode(Theme.Light.stringId)
    }

    /**
     * Executes validation steps.
     * Requires opening the dialog, performing the check, then closing the dialog again.
     */
    infix fun assert(func: ThemeAsserts.() -> Unit) =
        ThemeAsserts().apply(func)


    /**
     * Robot to check the results for the theme dialog.
     */
    class ThemeAsserts : DialogAsserts() {

        /**
         * Assert that the options are in the correct locations.
         */
        fun isLoadedCorrectly() {
            val collection = getRule().onAllNodesWithTag(DialogTags.OptionText, true)
            collection[0].assertTextContains(Theme.System.stringId.getResource())
            collection[1].assertTextContains(Theme.Dark.stringId.getResource())
            collection[2].assertTextContains(Theme.Light.stringId.getResource())
        }

        /**
         * Checks if the "System default" option is checked.
         */
        fun systemIsSelected() {
            getAllNodesWithTag(DialogTags.OptionRadio, true)[0].assertIsSelected()
        }

        /**
         * Checks if the "Dark" option is checked.
         */
        fun darkIsSelected() {
            getAllNodesWithTag(DialogTags.OptionRadio, true)[1].assertIsSelected()
        }

        /**
         * Checks if the "Light" option is checked.
         */
        fun lightIsSelected() {
            getAllNodesWithTag(DialogTags.OptionRadio, true)[2].assertIsSelected()
        }
    }
}
