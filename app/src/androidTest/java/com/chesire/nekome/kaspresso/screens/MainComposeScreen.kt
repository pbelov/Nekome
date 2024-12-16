package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen
import com.chesire.nekome.ui.MainActivityTags

fun mainScreen(
    func: MainComposeScreen.() -> Unit
) = MainComposeScreen().apply(func)

class MainComposeScreen : BaseComposeScreen<MainComposeScreen>() {

    // ACTIONS

    fun goToSearch() = goTo(MainActivityTags.Search)

    fun goToSettings() = goTo(MainActivityTags.Settings)

    fun goTo(tag: String) = getNodeWithTag(tag).performClick()

    infix fun assert(func: MainScreenAssert.() -> Unit) =
        MainScreenAssert().apply { func() }

    class MainScreenAssert : BaseComposeScreen<MainScreenAssert>() {

        // VALIDATIONS

        fun isOnScreen() = isOnScreen(MainActivityTags.Root)
    }
}