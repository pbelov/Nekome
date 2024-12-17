package com.chesire.nekome.kaspresso.screens

import com.chesire.nekome.app.series.collection.ui.SeriesCollectionTags
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen

fun seriesListScreen(
    func: SeriesListComposeScreen.() -> Unit
) = SeriesListComposeScreen().apply(func)

class SeriesListComposeScreen : BaseComposeScreen<SeriesListComposeScreen>() {

    val searchFAB = getNodeWithTag(SeriesCollectionTags.SearchFab)

    fun goToSearch() = searchFAB.performClick()
}