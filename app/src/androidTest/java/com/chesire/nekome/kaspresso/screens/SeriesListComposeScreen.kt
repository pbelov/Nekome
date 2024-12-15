package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.series.collection.ui.SeriesCollectionTags

fun seriesListScreen(
    func: SeriesListComposeScreen.() -> Unit
) = SeriesListComposeScreen().apply(func)

class SeriesListComposeScreen : BaseComposeScreen<SeriesListComposeScreen>() {

    // ACTIONS

    fun goToSearch() {
        getNodeWithTag(SeriesCollectionTags.SearchFab)
            .performClick()
    }

    // VALIDATIONS

}