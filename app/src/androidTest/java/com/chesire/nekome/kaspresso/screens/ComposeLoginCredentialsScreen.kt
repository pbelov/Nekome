package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.resources.StringResource
import io.github.kakaocup.kakao.common.utilities.getResourceString

/**
 * Method to interact with the [LoginCredentialsScreen].
 */
fun loginCredentials(
    func: ComposeLoginCredentialsScreen.() -> Unit
) = ComposeLoginCredentialsScreen().apply(func)

/**
 * Robot to interact with the login credentials screen.
 */
class ComposeLoginCredentialsScreen :
    BaseComposeScreen<ComposeLoginCredentialsScreen>() {

    /**
     * Enters the username.
     */
    fun enterUsername(username: String) {
        getNodeWithText(getResourceString(StringResource.login_username))
            .performTextInput(username)
    }

    /**
     * Enters the password.
     */
    fun enterPassword(password: String) {
        getNodeWithText(getResourceString(StringResource.login_password))
            .performTextInput(password)
    }

    /**
     * Clicks the login button.
     */
    fun clickLogin() {
        getNodeWithText(getResourceString(StringResource.login_login))
            .performClick()
    }

    /**
     * Clicks the forgot password button.
     */
    fun clickForgotPassword() {
        getNodeWithText(getResourceString(StringResource.login_forgot_password))
            .performClick()
    }

    /**
     * Clicks the sign up button.
     */
    fun clickSignUp() {
        getNodeWithText(getResourceString(StringResource.login_sign_up_kitsu))
            .performClick()
    }

    /**
     * Executes validation steps.
     */
    infix fun assert(func: LoginCredentialsResultAssert.() -> Unit) =
        LoginCredentialsResultAssert().apply { func() }
}

/**
 * Robot to check the results for the login details screen.
 */
class LoginCredentialsResultAssert : BaseComposeScreen<LoginCredentialsResultAssert>() {

    fun isOnScreen() {
        getNodeWithTag(CredentialsTags.Root)
            .assertIsDisplayed()
    }

    fun isLoginButtonEnabled() {
        getNodeWithText(getResourceString(StringResource.login_login))
            .assertIsEnabled()
    }

    fun isLoginButtonDisabled() {
        getNodeWithText(getResourceString(StringResource.login_login))
            .assertIsNotEnabled()
    }

    /**
     * Asserts the error for an empty email field.
     */
    fun isEmptyEmailError() {
        getNodeWithTag(CredentialsTags.Username)
        // TODO: Verify it is in error state
    }

    /**
     * Asserts the error for an empty password field.
     */
    fun isEmptyPasswordError() {
        getNodeWithTag(CredentialsTags.Password)
        // TODO: Verify it is in error state
    }

    /**
     * Asserts the error for having invalid credentials.
     */
    fun isInvalidCredentialsError() {
        checkSnackBarErrorText(getResourceString(StringResource.login_error_credentials))
    }

    /**
     * Asserts a generic network error.
     */
    fun isGenericError() {
        checkSnackBarErrorText(getResourceString(StringResource.login_error_generic))
    }
}
