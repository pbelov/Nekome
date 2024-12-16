package com.chesire.nekome.kaspresso.screens

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onAllNodesWithTag
import com.chesire.nekome.core.compose.composables.DialogTags
import com.chesire.nekome.core.preferences.flags.TitleLanguage
import com.chesire.nekome.helpers.getResource

class TitleLanguageComposeScreen : DialogComposeScreen<TitleLanguageComposeScreen>() {

    /**
     * Picks the "Canonical" option.
     */
    fun chooseCanonical() {
        clickOnNodeWithText(TitleLanguage.Canonical.stringId.getResource())
    }

    /**
     * Picks the "English" option.
     */
    fun chooseEnglish() {
        clickOnNodeWithText(TitleLanguage.English.stringId.getResource())
    }

    /**
     * Picks the "Romaji" option.
     */
    fun chooseRomaji() {
        clickOnNodeWithText(TitleLanguage.Romaji.stringId.getResource())
    }

    /**
     * Picks the "Japanese" option.
     */
    fun chooseJapanese() {
        clickOnNodeWithText(TitleLanguage.Japanese.stringId.getResource())
    }

    /**
     * Executes validation steps.
     * Requires opening the dialog, performing the check, then closing the dialog again.
     */
    infix fun assert(func: TitleLanguageAssert.() -> Unit) =
        TitleLanguageAssert().apply(func)
}

/**
 * Robot to check the results for the title language dialog.
 */
class TitleLanguageAssert: DialogAsserts() {

    /**
     * Assert that the options are in the correct locations.
     */
    fun isLoadedCorrectly() {
        val collection = getRule().onAllNodesWithTag(DialogTags.OptionText, true)
        collection[0].assertTextContains(TitleLanguage.Canonical.stringId.getResource())
        collection[1].assertTextContains(TitleLanguage.English.stringId.getResource())
        collection[2].assertTextContains(TitleLanguage.Romaji.stringId.getResource())
        collection[3].assertTextContains(TitleLanguage.Japanese.stringId.getResource())
    }

    /**
     * Checks if the "Canonical" option is checked.
     */
    fun canonicalIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[0].assertIsSelected()
    }

    /**
     * Checks if the "English" option is checked.
     */
    fun englishIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[1].assertIsSelected()
    }

    /**
     * Checks if the "Romaji" option is checked.
     */
    fun romajiIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[2].assertIsSelected()
    }

    /**
     * Checks if the "Japanese" option is checked.
     */
    fun japaneseIsSelected() {
        getAllNodesWithTag(DialogTags.OptionRadio, true)[3].assertIsSelected()
    }
}
