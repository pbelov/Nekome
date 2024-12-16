package com.chesire.nekome.kaspresso.tests

import com.chesire.nekome.datasource.auth.remote.AuthFailure
import com.chesire.nekome.kaspresso.Helpers
import com.chesire.nekome.kaspresso.screens.loginCredentialsScreen
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
            enterPassword(Helpers.testPassword)
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
            enterUsername(Helpers.testUsername)
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
        mockAuthErr(Helpers.testUsername, Helpers.testPassword, AuthFailure.InvalidCredentials)

        launchActivity()

        loginCredentialsScreen {
            enterUsername(Helpers.testUsername)
            enterPassword(Helpers.testPassword)
            clickLogin()

            assert {
                isInvalidCredentialsError()
            }
        }
    }

    @Test
    fun failureToLoginShowsError() {
        coEvery {
            authApi.login(Helpers.testUsername, Helpers.testPassword)
        } coAnswers {
            Err(AuthFailure.BadRequest)
        }

        launchActivity()

        loginCredentialsScreen {
            enterUsername(Helpers.testUsername)
            enterPassword(Helpers.testPassword)
            clickLogin()

            assert {
                isGenericError()
            }
        }
    }
}