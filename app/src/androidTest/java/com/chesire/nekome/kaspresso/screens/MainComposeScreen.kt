package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.series.collection.ui.SeriesCollectionTags
import com.chesire.nekome.kaspresso.checkAccess
import com.chesire.nekome.kaspresso.getResource
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen
import com.chesire.nekome.resources.StringResource
import com.chesire.nekome.ui.MainActivityTags
import io.github.kakaocup.compose.node.element.KNode

fun mainScreen(
    func: MainComposeScreen.() -> Unit
) = MainComposeScreen().apply(func)

open class MainComposeScreen : BaseComposeScreen<MainComposeScreen>() {

    // NODES

    val searchButton: KNode = getNodeWithTag(SeriesCollectionTags.SearchFab) // "Add new"

    val mainScreenRoot: KNode = getNodeWithTag(MainActivityTags.Root)
    val typeAnimeButton: KNode = getNodeWithTag(MainActivityTags.Anime)
    val typeMangaButton: KNode = getNodeWithTag(MainActivityTags.Manga)
    val settingsButton: KNode = getNodeWithTag(MainActivityTags.Settings)

    val noSeriesFound: KNode = getNodeWithText(StringResource.series_list_empty.getResource())

    // ACTIONS

    fun goToSettings() = settingsButton.performClick()

    fun goTo(tag: String) = getNodeWithTag(tag).performClick()

    infix fun assert(func: MainScreenAssert.() -> Unit) =
        MainScreenAssert().apply { func() }

    class MainScreenAssert : MainComposeScreen() {

        // VALIDATIONS

        fun isOnScreen() = mainScreenRoot.assertIsDisplayed()

        fun checkUI() {
            isOnScreen()

            searchButton.checkAccess()
            typeAnimeButton.checkAccess()
            typeMangaButton.checkAccess()
            settingsButton.checkAccess()
            noSeriesFound.assertIsDisplayed()
        }
    }
}