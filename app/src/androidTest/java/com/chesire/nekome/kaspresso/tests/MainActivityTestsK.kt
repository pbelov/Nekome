package com.chesire.nekome.kaspresso.tests

import com.chesire.nekome.core.preferences.flags.HomeScreenOptions
import com.chesire.nekome.kaspresso.screens.mainScreen
import com.chesire.nekome.kaspresso.tests.base.BaseTestK
import com.chesire.nekome.ui.MainActivityTags
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test


@HiltAndroidTest
class MainActivityTestsK : BaseTestK() {

    @Test
    fun overviewCanStartInAnimeView() {
        testCanStartWith(homeScreenOptions = HomeScreenOptions.Anime, tag = MainActivityTags.Anime)
    }

    @Test
    fun overviewCanStartInMangaView() {
        testCanStartWith(homeScreenOptions = HomeScreenOptions.Manga, tag = MainActivityTags.Manga)
    }

    @Test
    fun overviewCanNavigateToAnimeView() {
        testCanNavigateTo(homeScreenOptions = HomeScreenOptions.Manga, tag = MainActivityTags.Anime)
    }

    @Test
    fun overviewCanNavigateToMangaView() {
        testCanNavigateTo(homeScreenOptions = HomeScreenOptions.Anime, tag = MainActivityTags.Manga)
    }

    @Test
    fun overviewCanNavigateToSettingsView() {
        testCanNavigateTo(tag = MainActivityTags.Settings)
    }

    // methods below to avoid code duplication

    private fun testCanStartWith(homeScreenOptions: HomeScreenOptions? = null, tag: String) {
        launchWith(homeScreenOptions)

        checkScreen(tag)
    }

    private fun testCanNavigateTo(homeScreenOptions: HomeScreenOptions? = null, tag: String) {
        goTo(homeScreenOptions, tag)

        checkScreen(tag)
    }

    private fun checkScreen(tag: String) {
        mainScreen {
            assert {
                isOnScreen(tag)
            }
        }
    }
}