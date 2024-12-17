package com.chesire.nekome.kaspresso.screens.settings

import com.chesire.nekome.app.settings.config.ui.ConfigTags
import com.chesire.nekome.helpers.getResource
import com.chesire.nekome.resources.StringResource

/**
 * Method to interact with the [SettingsComposeScreen].
 */
fun settingsScreen(func: SettingsComposeScreen.() -> Unit) =
    SettingsComposeScreen().apply(func)

/**
 * Robot to interact with the config screen.
 */
open class SettingsComposeScreen : DialogComposeScreen<SettingsComposeScreen>() {

    override val root = getNodeWithTag(ConfigTags.Root)

    val licenses = getNodeWithText(StringResource.settings_licenses.getResource())
    val rateOnCompletion = getNodeWithText(StringResource.settings_rate_on_completion_summary.getResource())
    val theme = getNodeWithText(StringResource.settings_theme.getResource())
    val languageTitle = getNodeWithText(StringResource.settings_title_language_title.getResource())
    val imageQuality = getNodeWithText(StringResource.settings_image_quality_title.getResource())
    val defaultHome = getNodeWithText(StringResource.settings_default_home_title.getResource())
    val defaultSeriesStatus = getNodeWithText(StringResource.settings_default_series_status_title.getResource())

    /**
     * Opens the default series state dialog.
     */
    fun clickDefaultSeriesState() = defaultSeriesStatus.performClick()

    /**
     * Options for selecting the default series state.
     */
    fun defaultSeriesState(func: DefaultSeriesStateRobot.() -> Unit) =
        DefaultSeriesStateRobot().apply(func)

    /**
     * Opens the default home screen dialog.
     */
    fun clickDefaultHomeScreen() = defaultHome.performClick()

    /**
     * Options for selecting the default home screen.
     */
    fun defaultHomeScreen(func: DefaultHomeComposeScreen.() -> Unit) =
        DefaultHomeComposeScreen().apply(func)

    /**
     * Opens the image quality dialog.
     */
    fun clickImageQuality() = imageQuality.performClick()

    /**
     * Options for selecting the image quality.
     */
    fun imageQuality(func: ImageQualityComposeScreen.() -> Unit) =
        ImageQualityComposeScreen().apply(func)

    /**
     * Opens the title language dialog.
     */
    fun clickTitleLanguage() = languageTitle.performClick()
    /**
     * Options for selecting the title language.
     */
    fun titleLanguage(func: TitleLanguageComposeScreen.() -> Unit) =
        TitleLanguageComposeScreen().apply(func)

    /**
     * Opens the theme dialog.
     */
    fun clickTheme() = theme.performClick()

    /**
     * Options for selecting the theme.
     */
    fun changeTheme(func: ThemeComposeScreen.() -> Unit) = ThemeComposeScreen().apply(func)

    /**
     * Toggles the rate on completion setting.
     */
    fun changeRateOnComplete() = rateOnCompletion.performClick()

    /**
     * Open the licenses screen.
     */
    fun goToLicenses() = licenses.performClick()

    /**
     * Executes validation steps.
     */
    infix fun assert(func: SettingsScreenAsserts.() -> Unit) =
        SettingsScreenAsserts().apply(func)


    /**
     * Robot to check the results for the config screen.
     */
    class SettingsScreenAsserts : SettingsComposeScreen() {

        /**
         * Asserts the settings screen is shown.
         */
        fun isOnScreen() = root.assertIsDisplayed()
    }
}
