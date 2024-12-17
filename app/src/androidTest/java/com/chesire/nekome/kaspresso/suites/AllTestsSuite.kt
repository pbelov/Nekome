package com.chesire.nekome.kaspresso.suites

import com.chesire.nekome.kaspresso.tests.login.CredentialsTestsK
import com.chesire.nekome.kaspresso.tests.login.LoginFlowTestsK
import com.chesire.nekome.kaspresso.tests.main.MainActivityTestsK
import com.chesire.nekome.kaspresso.tests.search.SearchFunctionalTestsK
import com.chesire.nekome.kaspresso.tests.SettingsTestsK
import com.chesire.nekome.kaspresso.tests.login.LoginUITestsK
import com.chesire.nekome.kaspresso.tests.main.MainActivityUITestsK
import com.chesire.nekome.kaspresso.tests.search.SearchUITestsK
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    LoginUITestsK::class,
    CredentialsTestsK::class,
    LoginFlowTestsK::class,
    MainActivityUITestsK::class,
    MainActivityTestsK::class,
    SearchUITestsK::class,
    SearchFunctionalTestsK::class,
    SettingsTestsK::class
)

class AllTestsSuite