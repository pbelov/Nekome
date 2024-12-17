package com.chesire.nekome.kaspresso

import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.kakao.common.utilities.getResourceString

fun Int.getResource() = getResourceString(this)

object Helpers {
    const val TEST_USERNAME = "Username"
    const val TEST_PASSWORD = "Password"
}

fun KNode.checkAccess() {
    assertIsDisplayed()
    assertIsDisplayed()
}
