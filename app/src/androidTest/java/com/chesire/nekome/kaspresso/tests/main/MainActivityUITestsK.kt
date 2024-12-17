package com.chesire.nekome.kaspresso.tests.main

import com.chesire.nekome.kaspresso.screens.mainScreen
import com.chesire.nekome.kaspresso.tests.base.BaseTestK
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class MainActivityUITestsK : BaseTestK() {

    @Test
    fun checkUI() {
        mainScreen {
            assert {
                checkUI()
            }
        }
    }
}