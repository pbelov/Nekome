package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.kaspresso.getResource
import com.chesire.nekome.resources.StringResource

/**
 * Method to interact with the [LoginCredentialsScreen].
 */
fun loginCredentialsScreen(
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
        getNodeWithText(StringResource.login_username.getResource())
            .performTextInput(username)
    }

    /**
     * Enters the password.
     */
    fun enterPassword(password: String) {
        getNodeWithText(StringResource.login_password.getResource())
            .performTextInput(password)
    }

    /**
     * Clicks the login button.
     */
    fun clickLogin() {
        clickOnNodeWithText(StringResource.login_login.getResource())
    }

    /**
     * Clicks the forgot password button.
     */
    fun clickForgotPassword() {
        clickOnNodeWithText(StringResource.login_forgot_password.getResource())
    }

    /**
     * Clicks the sign up button.
     */
    fun clickSignUp() {
        clickOnNodeWithText(StringResource.login_sign_up_kitsu.getResource())
    }

    /**
     * Executes validation steps.
     */
    infix fun assert(func: LoginCredentialsAssert.() -> Unit) =
        LoginCredentialsAssert().apply { func() }
}

/**
 * Robot to check the results for the login details screen.
 */
class LoginCredentialsAssert : BaseComposeScreen<LoginCredentialsAssert>() {

    fun isOnScreen() = isOnScreen(CredentialsTags.Root)

    fun isLoginButtonEnabled() {
        getNodeWithText(StringResource.login_login.getResource()).assertIsEnabled()
    }

    fun isLoginButtonDisabled() {
        getNodeWithText(StringResource.login_login.getResource()).assertIsNotEnabled()
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
        checkSnackBarErrorText(StringResource.login_error_credentials.getResource())
    }

    /**
     * Asserts a generic network error.
     */
    fun isGenericError() {
        checkSnackBarErrorText(StringResource.login_error_generic.getResource())
    }
}
