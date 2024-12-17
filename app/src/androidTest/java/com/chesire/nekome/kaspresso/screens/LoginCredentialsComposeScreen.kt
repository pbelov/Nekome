package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.kaspresso.Helpers.checkAccess
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
open class LoginCredentialsComposeScreen : BaseComposeScreen<LoginCredentialsComposeScreen>() {

    val root = getNodeWithTag(CredentialsTags.Root)
    val usernameField = getNodeWithTag(CredentialsTags.Username)
    val passwordField = getNodeWithTag(CredentialsTags.Password)
    val loginButton = getNodeWithText(StringResource.login_login.getResource())
    val showPasswordButton = getNodeWithContentDesc(StringResource.login_show_password.getResource())
    val hidePasswordButton = getNodeWithContentDesc(StringResource.login_hide_password.getResource())
    val signUpButton = getNodeWithText(StringResource.login_sign_up_kitsu.getResource())
    val forgotPasswordButton = getNodeWithText(StringResource.login_forgot_password.getResource())

    val snackbar = getNodeWithTag(CredentialsTags.Snackbar)

    /**
     * Enters the username.
     */
    fun enterUsername(username: String) = usernameField.performTextInput(username)

    /**
     * Enters the password.
     */
    fun enterPassword(password: String) = passwordField.performTextInput(password)

    /**
     * Clicks the login button.
     */
    fun clickLogin() = loginButton.performClick()

    fun clickShowPassword() = showPasswordButton.performClick()

    /**
     * Clicks the forgot password button.
     */
    fun clickForgotPassword() = forgotPasswordButton.performClick()

    /**
     * Clicks the sign up button.
     */
    fun clickSignUp() = signUpButton.performClick()

    /**
     * Executes validation steps.
     */
    infix fun assert(func: LoginCredentialsAssert.() -> Unit) =
        LoginCredentialsAssert().apply { func() }
}

/**
 * Robot to check the results for the login details screen.
 */
class LoginCredentialsAssert : LoginCredentialsComposeScreen() {

    fun checkUI() {
        isOnScreen()

        usernameField.checkAccess()
        passwordField.checkAccess()
        forgotPasswordButton.checkAccess()
        signUpButton.checkAccess()

        isLoginButtonDisabled()
    }

    fun isOnScreen() = root.assertIsDisplayed()

    fun showPasswordButtonState(on: Boolean) {
        if (on) {
            showPasswordButton.assertIsDisplayed()
            hidePasswordButton.assertIsNotDisplayed()
        } else {
            showPasswordButton.assertIsNotDisplayed()
            hidePasswordButton.assertIsDisplayed()
        }
    }

    fun isLoginButtonEnabled() = loginButton.assertIsEnabled()

    fun isLoginButtonDisabled() = loginButton.assertIsNotEnabled()

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

    private fun checkSnackBarErrorText(
        text: String,
        substring: Boolean = true,
        ignoreCase: Boolean = true
    ) {
        snackbar
            .child<KNode> {
                hasAnyChild(
                    androidx.compose.ui.test.hasText(
                        text, ignoreCase = substring, substring = ignoreCase
                    )
                )
            }
            .assertIsDisplayed()
    }


}
