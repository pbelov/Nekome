package com.chesire.nekome.kaspresso.tests.search

import com.chesire.nekome.kaspresso.screens.searchScreen
import com.chesire.nekome.kaspresso.tests.base.SearchBaseTestK
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test


@HiltAndroidTest
class SearchUITestsK : SearchBaseTestK() {

    @Before
    override fun openSearchScreen() {
        super.openSearchScreen()
    }

    @Test
    fun checkSearchScreenUI() {
        searchScreen {
            assert {
                checkUI()
            }
        }
    }

    @Test
    fun seriesTypeDefaultSelection() {
        searchScreen {
            assert {
                isAnimeTypeSelected()
            }
        }
    }

    @Test
    fun mangaTypeSelectionAbility() {
        searchScreen {
            selectManga()

            assert {
                isMangaTypeSelected()
            }
        }
    }
}