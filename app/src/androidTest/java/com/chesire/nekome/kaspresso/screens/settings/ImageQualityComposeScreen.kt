package com.chesire.nekome.kaspresso.screens.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onAllNodesWithTag
import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.core.preferences.flags.ImageQuality
import com.chesire.nekome.helpers.getResource

open class ImageQualityComposeScreen : DialogComposeScreen<ImageQualityComposeScreen>() {

    /**
     * Picks the "Low" option.
     */
    fun chooseLow() {
        clickOnNodeWithText(ImageQuality.Low.stringId.getResource())
    }

    /**
     * Picks the "Medium" option.
     */
    fun chooseMedium() {
        clickOnNodeWithText(ImageQuality.Medium.stringId.getResource())
    }

    /**
     * Picks the "High" option.
     */
    fun chooseHigh() {
        clickOnNodeWithText(ImageQuality.High.stringId.getResource())
    }

    /**
     * Executes validation steps.
     * Requires opening the dialog, performing the check, then closing the dialog again.
     */
    infix fun assert(func: ImageQualityAssert.() -> Unit) =
        ImageQualityAssert().apply(func)
}

/**
 * Robot to check the results for the image quality dialog.
 */
class ImageQualityAssert : ImageQualityComposeScreen() {

    /**
     * Assert that the options are in the correct locations.
     */
    fun isLoadedCorrectly() {
        val collection = getRule().onAllNodesWithTag(DialogTags.OptionText, true)
        collection[0].assertTextContains(ImageQuality.Low.stringId.getResource())
        collection[1].assertTextContains(ImageQuality.Medium.stringId.getResource())
        collection[2].assertTextContains(ImageQuality.High.stringId.getResource())
    }

    /**
     * Checks if the "Low" option is checked.
     */
    fun lowIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[0].assertIsSelected()
    }

    /**
     * Checks if the "Medium" option is checked.
     */
    fun mediumIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[1].assertIsSelected()
    }

    /**
     * Checks if the "High" option is checked.
     */
    fun highIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[2].assertIsSelected()
    }
}
