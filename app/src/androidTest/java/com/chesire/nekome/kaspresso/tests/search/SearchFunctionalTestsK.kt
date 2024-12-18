package com.chesire.nekome.kaspresso.tests.search

import com.chesire.nekome.core.models.ErrorDomain
import com.chesire.nekome.datasource.search.remote.SearchApi
import com.chesire.nekome.kaspresso.screens.searchScreen
import com.chesire.nekome.kaspresso.tests.base.SearchBaseTestK
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

private const val GENERIC_ERROR = "GENERIC_ERROR"
private const val NO_RESULTS_ERROR = "NO_RESULTS_ERROR"

@HiltAndroidTest
class SearchFunctionalTestsK : SearchBaseTestK() {

    @Inject
    lateinit var searchApi: SearchApi

    @Before
    fun openStartScreen() {
        coEvery {
            searchApi.searchForAnime(GENERIC_ERROR)
        } coAnswers {
            Err(ErrorDomain.badRequest)
        }

        coEvery {
            searchApi.searchForAnime(NO_RESULTS_ERROR)
        } coAnswers {
            Ok(listOf())
        }

        super.openSearchScreen()
    }

    @Test
    fun emptySearchTermShowsError() {
        searchScreen {
            searchTerm("")
            selectAnime()
            clickSearch()

            assert {
                isEmptySearchError()
            }
        }
    }

    @Test
    fun genericErrorFromSearchShowsError() {
        searchScreen {
            searchTerm(GENERIC_ERROR)
            selectAnime()
            clickSearch()

            assert {
                isGenericError()
            }
        }
    }

    @Test
    fun noSeriesFoundErrorFromSearchShowsError() {
        searchScreen {
            searchTerm(NO_RESULTS_ERROR)
            selectAnime()
            clickSearch()

            assert {
                isNoSeriesFoundError()
            }
        }
    }
}