package com.chesire.nekome.kaspresso.tests.login

import com.chesire.nekome.kaspresso.screens.loginCredentialsScreen
import com.chesire.nekome.kaspresso.tests.base.BaseTestK
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class LoginUITestsK : BaseTestK(startLoggedIn = false) {

    @Before
    fun launch() {
        launchActivity()
    }

    @Test
    fun verifyUI() {
        loginCredentialsScreen {
            assert {
                checkUI()
            }
        }
    }

    @Test
    fun buttonShowPasswordDefaultStateTest() {
        loginCredentialsScreen {
            assert {
                showPasswordButtonState(true)
            }
        }
    }

    @Test
    fun buttonShowPasswordFlowTest() {
        loginCredentialsScreen {
            clickShowPassword()

            assert {
                showPasswordButtonState(false)
            }
        }
    }
}