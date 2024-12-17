package com.chesire.nekome.features.login

import com.chesire.nekome.UITest
import com.chesire.nekome.datasource.auth.remote.AuthApi
import com.chesire.nekome.datasource.auth.remote.AuthFailure
import com.chesire.nekome.kaspresso.Helpers
import com.chesire.nekome.robots.login.loginCredentials
import com.github.michaelbull.result.Err
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import javax.inject.Inject
import org.junit.Test

@HiltAndroidTest
class CredentialsTests : UITest() {

    override val startLoggedIn = false

    @Inject
    lateinit var authApi: AuthApi

    @Test
    fun emptyUsernameShowsError() {
        launchActivity()

        loginCredentials(composeTestRule) {
            enterUsername("")
            enterPassword(Helpers.TEST_PASSWORD)
            clickLogin()
        } validate {
            isEmptyEmailError()
        }
    }

    @Test
    fun emptyPasswordShowsError() {
        launchActivity()

        loginCredentials(composeTestRule) {
            enterUsername(Helpers.TEST_USERNAME)
            enterPassword("")
            clickLogin()
        } validate {
            isEmptyPasswordError()
        }
    }

    @Test
    fun invalidCredentialsShowsError() {
        coEvery {
            authApi.login(Helpers.TEST_USERNAME, Helpers.TEST_PASSWORD)
        } coAnswers {
            Err(AuthFailure.InvalidCredentials)
        }

        launchActivity()

        loginCredentials(composeTestRule) {
            enterUsername(Helpers.TEST_USERNAME)
            enterPassword(Helpers.TEST_PASSWORD)
            clickLogin()
        } validate {
            isInvalidCredentialsError()
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

        loginCredentials(composeTestRule) {
            enterUsername(Helpers.TEST_USERNAME)
            enterPassword(Helpers.TEST_PASSWORD)
            clickLogin()
        } validate {
            isGenericError()
        }
    }
}
