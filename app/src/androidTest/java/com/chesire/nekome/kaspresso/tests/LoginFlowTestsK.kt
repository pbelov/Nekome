package com.chesire.nekome.kaspresso.tests

import com.chesire.nekome.datasource.series.remote.SeriesApi
import com.chesire.nekome.datasource.user.remote.UserApi
import com.chesire.nekome.helpers.creation.createSeriesDomain
import com.chesire.nekome.helpers.creation.createUserDomain
import com.chesire.nekome.kaspresso.Helpers
import com.chesire.nekome.kaspresso.screens.loginCredentials
import com.chesire.nekome.kaspresso.screens.main
import com.github.michaelbull.result.Ok
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class LoginFlowTestsK : AuthBaseTestsK(startLoggedIn = false) {

    @Inject
    lateinit var seriesApi: SeriesApi

    @Inject
    lateinit var userApi: UserApi

    @Before
    fun setup() {
        mockAuthOK(Helpers.testUsername, Helpers.testPassword)

        coEvery {
            seriesApi.retrieveAnime(any())
        } coAnswers {
            Ok(listOf(createSeriesDomain()))
        }

        coEvery {
            seriesApi.retrieveManga(any())
        } coAnswers {
            Ok(listOf(createSeriesDomain()))
        }

        coEvery {
            userApi.getUserDetails()
        } coAnswers {
            Ok(createUserDomain())
        }
    }

    @Test
    fun navigateThroughLoginFlow() {
        launchActivity()

        loginCredentials {
            assert {
                isOnScreen()
                isLoginButtonDisabled()
            }

            enterUsername(Helpers.testUsername)
            enterPassword(Helpers.testPassword)

            // practice says that there is no failure if we trying to perform click on disabled button
            // that's why I added this additional check
            assert {
                isLoginButtonEnabled()
            }

            clickLogin()
        }

        main {
            assert {
                isOnScreen()
            }
        }
    }
}