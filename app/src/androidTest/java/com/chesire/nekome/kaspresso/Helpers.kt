package com.chesire.nekome.kaspresso

import io.github.kakaocup.kakao.common.utilities.getResourceString

fun Int.getResource() = getResourceString(this)

class Helpers {
    companion object {
        const val TEST_USERNAME = "Username"
        const val TEST_PASSWORD = "Password"
    }
}