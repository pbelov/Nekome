package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.login.credentials.ui.CredentialsTags
import com.chesire.nekome.ui.MainActivityTags

fun main(
    func: ComposeMainScreen.() -> Unit
) = ComposeMainScreen().apply(func)

class ComposeMainScreen : BaseComposeScreen<ComposeMainScreen>() {

    // ACTIONS

    fun goTo(tag: String) = getNodeWithTag(tag).performClick()

    infix fun assert(func: ComposeMainScreenAssert.() -> Unit) =
        ComposeMainScreenAssert().apply { func() }
}

class ComposeMainScreenAssert : BaseComposeScreen<ComposeMainScreenAssert>() {

    // VALIDATIONS

    fun isOnScreen() {
        getNodeWithTag(MainActivityTags.Root)
            .assertIsDisplayed()
    }

}