package com.chesire.nekome.kaspresso.tests.login

import com.chesire.nekome.kaspresso.screens.loginCredentialsScreen
import com.chesire.nekome.kaspresso.tests.base.BaseTestK
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class LoginUITestsK : BaseTestK(startLoggedIn = false) {

    companion object {
        private const val TAG = "LoginUITestsK"
    }

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
    fun buttonShowPasswordDefaultStateTest() = run {
        loginCredentialsScreen {
            assert {
                step("[$TAG]: check is show password button is in initial state") {
                    showPasswordButtonState(true)
                }
            }
        }
    }

    @Test
    fun buttonShowPasswordFlowTest() = run {
        loginCredentialsScreen {
            step("[$TAG]: click on show password button to check that it's status changed") {
                clickShowPassword()
            }

            assert {
                step("[$TAG]: check that show button state changed to opposite (hide password)") {
                    showPasswordButtonState(false)
                }
            }
        }
    }
}