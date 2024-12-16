package com.chesire.nekome.kaspresso

import com.chesire.nekome.kaspresso.tests.CredentialsTestsK
import com.chesire.nekome.kaspresso.tests.LoginFlowTestsK
import com.chesire.nekome.kaspresso.tests.MainActivityTestsK
import com.chesire.nekome.kaspresso.tests.SearchTestsK
import com.chesire.nekome.kaspresso.tests.SettingsTestsK
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    CredentialsTestsK::class,
    LoginFlowTestsK::class,
    MainActivityTestsK::class,
    SearchTestsK::class,
    SettingsTestsK::class
)

class AllTestsSuite