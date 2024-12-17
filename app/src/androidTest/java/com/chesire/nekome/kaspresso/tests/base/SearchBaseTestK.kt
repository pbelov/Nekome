package com.chesire.nekome.kaspresso.tests.base

import com.chesire.nekome.kaspresso.screens.seriesListScreen
import dagger.hilt.android.testing.HiltAndroidTest

@HiltAndroidTest
open class SearchBaseTestK : BaseTestK() {

    open fun openSearchScreen() {
        launchActivity()

        seriesListScreen {
            goToSearch()
        }
    }
}