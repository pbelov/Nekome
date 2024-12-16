package com.chesire.nekome.kaspresso.tests.base

import com.chesire.nekome.datasource.auth.remote.AuthApi
import com.chesire.nekome.datasource.auth.remote.AuthDomain
import com.chesire.nekome.datasource.auth.remote.AuthFailure
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import javax.inject.Inject

@HiltAndroidTest
open class AuthBaseTestsK(startLoggedIn: Boolean = false) : BaseTestK(startLoggedIn = startLoggedIn) {

    @Inject
    lateinit var authApi: AuthApi

    fun mockAuthOK(
        username: String,
        password: String
    ) {
        coEvery {
            authApi.login(username, password)
        } coAnswers {
            Ok(AuthDomain("accessToken", "refreshToken"))
        }
    }

    fun mockAuthErr(
        username: String,
        password: String,
        error: AuthFailure
    ) {
        coEvery {
            authApi.login(username, password)
        } coAnswers {
            Err(error)
        }
    }
}