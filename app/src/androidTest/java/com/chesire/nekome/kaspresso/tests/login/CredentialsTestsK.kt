package com.chesire.nekome.kaspresso.tests.login

import com.chesire.nekome.datasource.auth.remote.AuthFailure
import com.chesire.nekome.kaspresso.Helpers
import com.chesire.nekome.kaspresso.screens.loginCredentialsScreen
import com.chesire.nekome.kaspresso.tests.base.AuthBaseTestsK
import com.github.michaelbull.result.Err
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import org.junit.Test

@HiltAndroidTest
class CredentialsTestsK : AuthBaseTestsK(startLoggedIn = false) {

    @Test
    fun emptyUsernameShowsError() {
        launchActivity()

        loginCredentialsScreen {
            enterUsername("")
            enterPassword(Helpers.TEST_PASSWORD)
            assert {
                isLoginButtonDisabled()
            }

            // just in case
            clickLogin()

            assert {
                isOnScreen()
            }
        }
    }

    @Test
    fun emptyPasswordShowsError() {
        launchActivity()

        loginCredentialsScreen {
            enterUsername(Helpers.TEST_USERNAME)
            enterPassword("")
            clickLogin()

            assert {
                isLoginButtonDisabled()
            }

            // just in case
            clickLogin()

            assert {
                isOnScreen()
            }
        }
    }

    @Test
    fun invalidCredentialsShowsError() {
        mockAuthErr(Helpers.TEST_USERNAME, Helpers.TEST_PASSWORD, AuthFailure.InvalidCredentials)

        launchActivity()

        loginCredentialsScreen {
            enterUsername(Helpers.TEST_USERNAME)
            enterPassword(Helpers.TEST_PASSWORD)
            clickLogin()

            assert {
                isInvalidCredentialsError()
            }
        }
    }

    @Test
    fun failureToLoginShowsError() {
        coEvery {
            authApi.login(Helpers.TEST_USERNAME, Helpers.TEST_PASSWORD)
        } coAnswers {
            Err(AuthFailure.BadRequest)
        }

        launchActivity()

        loginCredentialsScreen {
            enterUsername(Helpers.TEST_USERNAME)
            enterPassword(Helpers.TEST_PASSWORD)
            clickLogin()

            assert {
                isGenericError()
            }
        }
    }
}