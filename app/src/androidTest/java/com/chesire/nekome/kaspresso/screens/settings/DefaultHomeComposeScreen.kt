package com.chesire.nekome.kaspresso.screens.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.performClick
import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.core.preferences.flags.HomeScreenOptions
import com.chesire.nekome.helpers.getResource
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen

/**
 * Robot to interact with the default home screen dialog.
 */
class DefaultHomeComposeScreen : DialogComposeScreen<DefaultHomeComposeScreen>() {

    /**
     * Picks the "Anime" option.
     */
    fun chooseAnime() {
        getAllNodesWithText(HomeScreenOptions.Anime.stringId.getResource())
            .filterToOne(hasParent(hasTestTag(DialogTags.Root)))
            .performClick()
    }

    /**
     * Picks the "Manga" option.
     */
    fun chooseManga() {
        getAllNodesWithText(HomeScreenOptions.Manga.stringId.getResource())
            .filterToOne(hasParent(hasTestTag(DialogTags.Root)))
            .performClick()
    }

    /**
     * Executes validation steps.
     * Requires opening the dialog, performing the check, then closing the dialog again.
     */
    infix fun assert(func: DefaultHomeScreenAsserts.() -> Unit) =
        DefaultHomeScreenAsserts().apply(func)
}

/**
 * Robot to check the results for the default home screen dialog.
 */
class DefaultHomeScreenAsserts : BaseComposeScreen<DefaultHomeScreenAsserts>() {

    /**
     * Assert that the options are in the correct locations.
     */
    fun isLoadedCorrectly() {
        val collection = getAllNodesWithTag(DialogTags.OptionText, true)
        collection[0].assertTextContains(HomeScreenOptions.Anime.stringId.getResource())
        collection[1].assertTextContains(HomeScreenOptions.Manga.stringId.getResource())
    }

    /**
     * Checks if the "Anime" option is checked.
     */
    fun animeIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[0]
            .assertIsSelected()
    }

    /**
     * Checks if the "Manga" option is checked.
     */
    fun mangaIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[1]
            .assertIsSelected()
    }
}
