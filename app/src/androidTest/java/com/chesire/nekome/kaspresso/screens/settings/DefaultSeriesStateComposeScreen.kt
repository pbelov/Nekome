package com.chesire.nekome.kaspresso.screens.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onAllNodesWithTag
import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.core.flags.UserSeriesStatus
import com.chesire.nekome.helpers.getResource
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen

/**
 * Robot to interact with the default series state dialog.
 */
class DefaultSeriesStateRobot : DialogComposeScreen<DefaultSeriesStateRobot>() {

    /**
     * Picks the "Current" option.
     */
    fun chooseCurrent() = clickOnNodeWithText(UserSeriesStatus.Current.stringId.getResource())

    /**
     * Picks the "Completed" option.
     */
    fun chooseCompleted() = clickOnNodeWithText(UserSeriesStatus.Completed.stringId.getResource())

    /**
     * Picks the "On hold" option.
     */
    fun chooseOnHold() = clickOnNodeWithText(UserSeriesStatus.OnHold.stringId.getResource())

    /**
     * Picks the "Dropped" option.
     */
    fun chooseDropped() = clickOnNodeWithText(UserSeriesStatus.Dropped.stringId.getResource())

    /**
     * Picks the "Planned" option.
     */
    fun choosePlanned() = clickOnNodeWithText(UserSeriesStatus.Planned.stringId.getResource())

    /**
     * Executes validation steps.
     * Requires opening the dialog, performing the check, then closing the dialog again.
     */
    infix fun assert(func: DefaultSeriesStateAssert.() -> Unit) =
        DefaultSeriesStateAssert().apply(func)
}

/**
 * Robot to check the results for the default series state dialog.
 */
class DefaultSeriesStateAssert : DialogAsserts() {

    /**
     * Assert that the options are in the correct locations.
     */
    // TODO: move indexes to separate constants
    fun isLoadedCorrectly() {
        val collection = BaseComposeScreen.getRule()
            .onAllNodesWithTag(DialogTags.OptionText, true)
        collection[0].assertTextContains(UserSeriesStatus.Current.stringId.getResource())
        collection[1].assertTextContains(UserSeriesStatus.Completed.stringId.getResource())
        collection[2].assertTextContains(UserSeriesStatus.OnHold.stringId.getResource())
        collection[3].assertTextContains(UserSeriesStatus.Dropped.stringId.getResource())
        collection[4].assertTextContains(UserSeriesStatus.Planned.stringId.getResource())
    }

    /**
     * Checks if the "Current" option is checked.
     */
    fun currentIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[0].assertIsSelected()
    }

    /**
     * Checks if the "Completed" option is checked.
     */
    fun completedIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[1].assertIsSelected()
    }

    /**
     * Checks if the "On hold" option is checked.
     */
    fun onHoldIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[2].assertIsSelected()
    }

    /**
     * Checks if the "Dropped" option is checked.
     */
    fun droppedIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[3].assertIsSelected()
    }

    /**
     * Checks if the "Planned" option is checked.
     */
    fun plannedIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[4].assertIsSelected()
    }
}