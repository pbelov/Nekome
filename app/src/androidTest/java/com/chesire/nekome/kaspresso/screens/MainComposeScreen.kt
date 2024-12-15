package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.ui.MainActivityTags

fun main(
    func: MainComposeScreen.() -> Unit
) = MainComposeScreen().apply(func)

class MainComposeScreen : BaseComposeScreen<MainComposeScreen>() {

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