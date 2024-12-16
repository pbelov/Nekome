package com.chesire.nekome.kaspresso

import io.github.kakaocup.kakao.common.utilities.getResourceString

fun Int.getResource() = getResourceString(this)

class Helpers {
    companion object {
        val testUsername = "Username"
        val testPassword = "Password"
    }
}