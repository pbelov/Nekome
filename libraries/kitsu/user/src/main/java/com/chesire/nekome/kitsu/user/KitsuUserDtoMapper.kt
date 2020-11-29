package com.chesire.nekome.kitsu.user

import com.chesire.nekome.core.flags.Service
import com.chesire.nekome.core.models.ImageModel
import com.chesire.nekome.kitsu.user.dto.UserItemDto
import com.chesire.nekome.user.api.UserDomain
import javax.inject.Inject

/**
 * Provides ability to map instances of [UserItemDto] into [UserDomain].
 */
class KitsuUserDtoMapper @Inject constructor() {

    fun from(input: UserItemDto) =
        UserDomain(
            input.id,
            input.attributes.name,
            input.attributes.avatar ?: ImageModel.empty,
            input.attributes.coverImage ?: ImageModel.empty,
            Service.Kitsu
        )
}
