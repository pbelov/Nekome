package com.chesire.nekome.kaspresso.tests.login

import com.chesire.nekome.kaspresso.screens.loginCredentialsScreen
import com.chesire.nekome.kaspresso.tests.base.BaseTestK
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class LoginUITestsK : BaseTestK(startLoggedIn = false) {

    @Test
    fun verifyUI() {
        launchActivity()

        loginCredentialsScreen {
            assert {
                isOnScreen()
                isLoginButtonDisabled()
                isLoginFieldExistsAndEditable()
                isPasswordFieldExistsAndEditable()
            }
        }
    }

    @Test
    fun buttonShowPasswordDefaultStateTest() {
        launchActivity()

        loginCredentialsScreen {
            assert {
                showPasswordButtonState(true)
            }
        }
    }

    @Test
    fun buttonShowPasswordFlowTest() {
        launchActivity()

        loginCredentialsScreen {
            clickShowPassword()
            assert {
                showPasswordButtonState(false)
            }
        }
    }
}