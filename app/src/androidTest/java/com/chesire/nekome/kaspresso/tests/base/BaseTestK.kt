package com.chesire.nekome.kaspresso.tests.base

import android.app.Activity
import android.os.SystemClock
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.rule.cleardata.ClearDatabaseRule
import com.adevinta.android.barista.rule.cleardata.ClearPreferencesRule
import com.chesire.nekome.core.preferences.ApplicationPreferences
import com.chesire.nekome.core.preferences.SeriesPreferences
import com.chesire.nekome.core.preferences.flags.HomeScreenOptions
import com.chesire.nekome.database.dao.SeriesDao
import com.chesire.nekome.database.dao.UserDao
import com.chesire.nekome.datasource.auth.local.AuthProvider
import com.chesire.nekome.helpers.createTestUser
import com.chesire.nekome.helpers.login
import com.chesire.nekome.helpers.logout
import com.chesire.nekome.helpers.reset
import com.chesire.nekome.kaspresso.screens.base.BaseComposeScreen
import com.chesire.nekome.ui.MainActivity
import com.kaspersky.components.composesupport.config.withComposeSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * K is for Kaspresso. just to avoid class name duplication with existing ones
 */
@RunWith(AndroidJUnit4::class)
open class BaseTestK(
    private val activityClass: Class<out Activity> = MainActivity::class.java,
    private val startLoggedIn: Boolean = true,
    private val launchActivity: Boolean = true
) : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withComposeSupport()
) {

    @Suppress("LeakingThis")
    @get:Rule
    val hilt = HiltAndroidRule(this)

    @get:Rule
    val clearDatabase = ClearDatabaseRule()

    @get:Rule
    val clearPreferences = ClearPreferencesRule()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var authProvider: AuthProvider

    @Inject
    lateinit var series: SeriesDao

    @Inject
    lateinit var user: UserDao

    @Inject
    lateinit var applicationPreferences: ApplicationPreferences

    @Inject
    lateinit var seriesPreferences: SeriesPreferences

    /**
     * Initial setup method.
     */
    @Before
    open fun setUp() {
        device.uiDevice.wakeUp()

        BaseComposeScreen.setRule(composeTestRule)

        hilt.inject()

        runBlocking {
            applicationPreferences.reset()
            seriesPreferences.reset()
        }

        if (startLoggedIn) {
            authProvider.login()
            user.createTestUser()
        } else {
            authProvider.logout()
        }

        if (launchActivity) {
            launchActivity()
        }
    }

    /**
     * Launches the [Activity] using the [ActivityScenario].
     */
    protected fun launchActivity() {
        ActivityScenario.launch(activityClass)
        // Not the nicest solution, but it keeps compose views a bit happier when they launch.
        SystemClock.sleep(200)
    }

    // [pbelov]: can be moved to specific test, but I'd like to leave it here for any case in the future
    protected fun launchWith(homeScreenOptions: HomeScreenOptions? = null) {
        if (homeScreenOptions != null) {
            runBlocking {
                applicationPreferences.updateDefaultHomeScreen(homeScreenOptions)
            }
        }

        launchActivity()
    }
}