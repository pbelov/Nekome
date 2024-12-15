package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.app.search.search.ui.SearchTags
import com.chesire.nekome.resources.StringResource
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.kakao.common.utilities.getResourceString

fun searchScreen(
    func: SearchComposeScreen.() -> Unit
) = SearchComposeScreen().apply(func)

class SearchComposeScreen : BaseComposeScreen<SearchComposeScreen>() {

    /**
     * Enters the term to search for.
     */
    fun searchTerm(term: String) {
        getNodeWithTag(SearchTags.Input)
            .performTextInput(term)
    }

    /**
     * Selects the anime chip.
     */
    fun selectAnime() {
        getNodeWithTag(SearchTags.Anime)
            .performClick()
    }

    /**
     * Selects the manga chip.
     */
    fun selectManga() {
        getNodeWithTag(SearchTags.Manga)
            .performClick()
    }

    /**
     * Clicks the search button.
     */
    fun clickSearch() {
        getNodeWithTag(SearchTags.Search)
            .performClick()
    }

    /**
     * Executes validation steps.
     */
    infix fun assert(func: SearchScreenAssert.() -> Unit) =
        SearchScreenAssert().apply(func)
}

/**
 * Robot to check the results for the search screen.
 */
class SearchScreenAssert : BaseComposeScreen<SearchScreenAssert>() {

    /**
     * Asserts the search screen is shown.
     */
    fun isOnScreen() {
        getNodeWithTag(SearchTags.Root)
            .assertIsDisplayed()
    }

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

