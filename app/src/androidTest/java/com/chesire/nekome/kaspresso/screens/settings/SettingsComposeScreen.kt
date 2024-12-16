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
class SettingsComposeScreen : DialogComposeScreen<SettingsComposeScreen>() {

    /**
     * Opens the default series state dialog.
     */
    fun clickDefaultSeriesState() {
        clickOnNodeWithText(StringResource.settings_default_series_status_title.getResource())
    }

    /**
     * Options for selecting the default series state.
     */
    fun defaultSeriesState(func: DefaultSeriesStateRobot.() -> Unit) =
        DefaultSeriesStateRobot().apply(func)

    /**
     * Opens the default home screen dialog.
     */
    fun clickDefaultHomeScreen() {
        clickOnNodeWithText(StringResource.settings_default_home_title.getResource())
    }

    /**
     * Options for selecting the default home screen.
     */
    fun defaultHomeScreen(func: DefaultHomeComposeScreen.() -> Unit) =
        DefaultHomeComposeScreen().apply(func)

    /**
     * Opens the image quality dialog.
     */
    fun clickImageQuality() {
        clickOnNodeWithText(StringResource.settings_image_quality_title.getResource())
    }

    /**
     * Options for selecting the image quality.
     */
    fun imageQuality(func: ImageQualityComposeScreen.() -> Unit) =
        ImageQualityComposeScreen().apply(func)

    /**
     * Opens the title language dialog.
     */
    fun clickTitleLanguage() {
        clickOnNodeWithText(StringResource.settings_title_language_title.getResource())
    }

    /**
     * Options for selecting the title language.
     */
    fun titleLanguage(func: TitleLanguageComposeScreen.() -> Unit) =
        TitleLanguageComposeScreen().apply(func)

    /**
     * Opens the theme dialog.
     */
    fun clickTheme() {
        clickOnNodeWithText(StringResource.settings_theme.getResource())
    }

    /**
     * Options for selecting the theme.
     */
    fun changeTheme(func: ThemeComposeScreen.() -> Unit) = ThemeComposeScreen().apply(func)

    /**
     * Toggles the rate on completion setting.
     */
    fun changeRateOnComplete() {
        clickOnNodeWithText(StringResource.settings_rate_on_completion_summary.getResource())
    }

    /**
     * Open the licenses screen.
     */
    fun goToLicenses() {
        clickOnNodeWithText(StringResource.settings_licenses.getResource())
    }

    /**
     * Executes validation steps.
     */
    infix fun assert(func: SettingsScreenAsserts.() -> Unit) =
        SettingsScreenAsserts().apply(func)


    /**
     * Robot to check the results for the config screen.
     */
    class SettingsScreenAsserts : DialogAsserts() {

        /**
         * Asserts the settings screen is shown.
         */
        override fun isOnScreen() = isOnScreen(ConfigTags.Root)
    }
}
