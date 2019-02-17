package com.chesire.malime.kitsu.api.library

import com.chesire.malime.core.Resource
import com.chesire.malime.core.api.LibraryApi
import com.chesire.malime.core.models.SeriesModel
import kotlinx.coroutines.Deferred
import retrofit2.Response

private const val LIMIT = 500
private const val MAX_RETRIES = 3

class KitsuLibrary(
    private val libraryService: KitsuLibraryService,
    private val userId: Int
) : LibraryApi {
    override suspend fun retrieveAnime() = performRetrieveCall(libraryService::retrieveAnimeAsync)
    override suspend fun retrieveManga() = performRetrieveCall(libraryService::retrieveMangaAsync)

    private suspend fun performRetrieveCall(
        execute: (Int, Int, Int) -> Deferred<Response<ParsedLibraryResponse>>
    ): Resource<List<SeriesModel>> {
        val models = mutableListOf<SeriesModel>()

        var offset = 0
        var page = 0
        var retries = 0
        var errorMessage = ""
        var executeAgain: Boolean

        do {
            executeAgain = false

            val result = execute(userId, offset, LIMIT).await()
            if (result.isSuccessful && result.body() != null) {
                val body = result.body()!!
                models.addAll(body.series)
                retries = 0

                if (!body.links.next.isEmpty()) {
                    page++
                    offset = LIMIT * page
                    executeAgain = true
                }
            } else {
                if (retries < MAX_RETRIES) {
                    retries++
                    executeAgain = true
                } else {
                    errorMessage = result.message()
                }
            }
        } while (executeAgain)

        return if (retries == MAX_RETRIES && models.count() == 0) {
            Resource.Error(errorMessage)
        } else {
            Resource.Success(models)
        }
    }
}
