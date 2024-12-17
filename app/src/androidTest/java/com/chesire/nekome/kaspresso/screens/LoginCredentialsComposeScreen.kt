package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.kaspresso.getResource
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen
import com.chesire.nekome.resources.StringResource
import io.github.kakaocup.compose.node.element.KNode

/**
 * Method to interact with the [LoginCredentialsComposeScreen].
 */
fun loginCredentialsScreen(
    func: LoginCredentialsComposeScreen.() -> Unit
) = LoginCredentialsComposeScreen().apply(func)

/**
 * Robot to interact with the login credentials screen.
 */
class LoginCredentialsComposeScreen :
    BaseComposeScreen<LoginCredentialsComposeScreen>() {

    /**
     * Enters the username.
     */
    fun enterUsername(username: String) {
        getNodeWithTag(StringResource.login_username.getResource())
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

    fun clickShowPassword() {
        clickOnNodeWithContentDesc(StringResource.login_show_password.getResource())
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

    fun isLoginFieldExistsAndEditable() {
        getNodeWithTag(CredentialsTags.Username).apply {
            assertIsDisplayed()
            assertIsEnabled()
        }
    }

    fun isPasswordFieldExistsAndEditable() {
        getNodeWithTag(CredentialsTags.Password).apply {
            assertIsDisplayed()
            assertIsEnabled()
        }
    }

    fun showPasswordButtonState(on: Boolean) {
        val node = if (on) {
            getNodeWithContentDesc(StringResource.login_show_password.getResource())
        } else {
            getNodeWithContentDesc(StringResource.login_hide_password.getResource())
        }

        node.assertIsDisplayed()
    }

    fun isForgotPasswordButtonExists() {
        getNodeWithText(StringResource.login_forgot_password.getResource())
    }

    fun isSignUpButtonExists() {
        getNodeWithText(StringResource.login_sign_up_kitsu.getResource())
    }

    fun isLoginButtonEnabled() {
        getNodeWithText(StringResource.login_login.getResource()).assertIsEnabled()
    }

    fun isLoginButtonDisabled() {
        getNodeWithText(StringResource.login_login.getResource()).assertIsNotEnabled()
    }

    /**
     * Asserts the error for an empty email field.
     */
    // is not actual on current version as of login button is not enabled in this case
//    fun isEmptyEmailError() {
//        getNodeWithTag(CredentialsTags.Username)
//        // TODO: Verify it is in error state
//    }

    /**
     * Asserts the error for an empty password field.
     */
    // is not actual on current version as of login button is not enabled in this case
//    fun isEmptyPasswordError() {
//        getNodeWithTag(CredentialsTags.Password)
//        // TODO: Verify it is in error state
//    }

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
