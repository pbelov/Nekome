package com.chesire.nekome.account

import com.chesire.nekome.core.flags.Service
import com.chesire.nekome.core.models.ImageModel
import com.chesire.nekome.testing.createImageModel
import com.chesire.nekome.user.api.UserDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDomainMapperTests {

    private val map = UserDomainMapper()

    @Test
    fun `toUserModel converts UserDomain to UserModel`() {
        val input = UserDomain(
            15,
            "name",
            createImageModel(
                ImageModel.ImageData("avatarTiny", 10, 5),
                ImageModel.ImageData("avatarSmall", 20, 10),
                ImageModel.ImageData("avatarMedium", 30, 15),
                ImageModel.ImageData("avatarLarge", 40, 20),
            ),
            createImageModel(
                ImageModel.ImageData("coverTiny", 5, 10),
                ImageModel.ImageData("coverSmall", 10, 20),
                ImageModel.ImageData("coverMedium", 15, 30),
                ImageModel.ImageData("coverLarge", 20, 40),
            ),
            Service.Kitsu
        )

        val output = map.toUserModel(input)

        assertEquals(input.userId, output.userId)
        assertEquals(input.name, output.name)
        assertEquals(input.avatar, output.avatar)
        assertEquals(input.coverImage, output.coverImage)
        assertEquals(input.service, output.service)
    }
}
