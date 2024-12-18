package com.chesire.nekome.kaspresso.screens.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onAllNodesWithTag
import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.core.flags.UserSeriesStatus
import com.chesire.nekome.helpers.getResource

/**
 * Robot to interact with the default series state dialog.
 */
open class DefaultSeriesStateRobot : DialogComposeScreen<DefaultSeriesStateRobot>() {

    private val statusCurrent = getNodeWithText(UserSeriesStatus.Current.stringId.getResource())
    private val statusCompleted = getNodeWithText(UserSeriesStatus.Completed.stringId.getResource())
    private val statusOnHold = getNodeWithText(UserSeriesStatus.OnHold.stringId.getResource())
    private val statusDropped = getNodeWithText(UserSeriesStatus.Dropped.stringId.getResource())
    private val statusPlanned = getNodeWithText(UserSeriesStatus.Planned.stringId.getResource())

    /**
     * Picks the "Current" option.
     */
    fun chooseCurrent() = statusCurrent.performClick()

    /**
     * Picks the "Completed" option.
     */
    fun chooseCompleted() = statusCompleted.performClick()

    /**
     * Picks the "On hold" option.
     */
    fun chooseOnHold() = statusOnHold.performClick()

    /**
     * Picks the "Dropped" option.
     */
    fun chooseDropped() = statusDropped.performClick()

    /**
     * Picks the "Planned" option.
     */
    fun choosePlanned() = statusPlanned.performClick()

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
class DefaultSeriesStateAssert : DefaultSeriesStateRobot() {

    companion object {
        private const val INDEX_STATUS_CURRENT = 0
        private const val INDEX_STATUS_COMPLETED = 1
        private const val INDEX_STATUS_ON_HOLD = 2
        private const val INDEX_STATUS_DROPPED = 3
        private const val INDEX_STATUS_PLANNED = 4
    }

    /**
     * Assert that the options are in the correct locations.
     */
    fun isLoadedCorrectly() {
        val collection = getRule().onAllNodesWithTag(DialogTags.OptionText, true)

        collection[INDEX_STATUS_CURRENT].assertTextContains(UserSeriesStatus.Current.stringId.getResource())
        collection[INDEX_STATUS_COMPLETED].assertTextContains(UserSeriesStatus.Completed.stringId.getResource())
        collection[INDEX_STATUS_ON_HOLD].assertTextContains(UserSeriesStatus.OnHold.stringId.getResource())
        collection[INDEX_STATUS_DROPPED].assertTextContains(UserSeriesStatus.Dropped.stringId.getResource())
        collection[INDEX_STATUS_PLANNED].assertTextContains(UserSeriesStatus.Planned.stringId.getResource())
    }

    /**
     * Checks if the "Current" option is checked.
     */
    fun currentIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[INDEX_STATUS_CURRENT].assertIsSelected()
    }

    /**
     * Checks if the "Completed" option is checked.
     */
    fun completedIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[INDEX_STATUS_COMPLETED].assertIsSelected()
    }

    /**
     * Checks if the "On hold" option is checked.
     */
    fun onHoldIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[INDEX_STATUS_ON_HOLD].assertIsSelected()
    }

    /**
     * Checks if the "Dropped" option is checked.
     */
    fun droppedIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[INDEX_STATUS_DROPPED].assertIsSelected()
    }

    /**
     * Checks if the "Planned" option is checked.
     */
    fun plannedIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[INDEX_STATUS_PLANNED].assertIsSelected()
    }
}