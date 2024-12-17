package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.search.search.ui.SearchTags
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen
import com.chesire.nekome.resources.StringResource
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.kakao.common.utilities.getResourceString

fun searchScreen(
    func: SearchComposeScreen.() -> Unit
) = SearchComposeScreen().apply(func)

open class SearchComposeScreen : BaseComposeScreen<SearchComposeScreen>() {

    val root : KNode = getNodeWithTag(SearchTags.Root)
    val searchField : KNode = getNodeWithTag(SearchTags.Input)
    val animeType : KNode = getNodeWithTag(SearchTags.Anime)
    val mangaType : KNode = getNodeWithTag(SearchTags.Manga)
    val searchButton : KNode = getNodeWithTag(SearchTags.Search)

    /**
     * Enters the term to search for.
     */
    fun searchTerm(term: String) = searchField.performTextInput(term)

    /**
     * Selects the anime chip.
     */
    fun selectAnime() = animeType.performClick()

    /**
     * Selects the manga chip.
     */
    fun selectManga() = mangaType.performClick()

    /**
     * Clicks the search button.
     */
    fun clickSearch() = searchButton.performClick()

    /**
     * Executes validation steps.
     */
    infix fun assert(func: SearchScreenAssert.() -> Unit) =
        SearchScreenAssert().apply(func)
}

/**
 * Robot to check the results for the search screen.
 */
class SearchScreenAssert : SearchComposeScreen() {

    fun checkUI() {
        isOnScreen()

        searchField.assertIsDisplayed()
        animeType.assertIsDisplayed()
        mangaType.assertIsDisplayed()
        searchButton.assertIsDisplayed()
    }

    fun isAnimeTypeSelected() = animeType.assertIsSelected()

    fun isMangaTypeSelected() = mangaType.assertIsSelected()

    /**
     * Asserts the search screen is shown.
     */
    fun isOnScreen() = root.assertIsDisplayed()

    /**
     * Asserts the error for having no search term.
     */
    fun isEmptySearchError() {
        checkForAlertDisplayed(getResourceString(StringResource.search_error_no_text))
    }

    /**
     * Asserts the generic error message.
     */
    fun isGenericError() {
        checkForAlertDisplayed(getResourceString(StringResource.error_generic))
    }

    /**
     * Asserts the error for no series found.
     */
    fun isNoSeriesFoundError() {
        checkForAlertDisplayed(getResourceString(StringResource.search_error_no_series_found))
    }

    private fun checkForAlertDisplayed(text: String) {
        getNodeWithTag(SearchTags.Snackbar).child<KNode> {
            hasAnyChild(
                androidx.compose.ui.test.hasText(
                    text = text,
                    ignoreCase = true,
                    substring = true
                )
            )
        }.assertIsDisplayed()
    }
}

