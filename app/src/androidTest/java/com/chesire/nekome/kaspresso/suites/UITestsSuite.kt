package com.chesire.nekome.kaspresso.suites

import com.chesire.nekome.kaspresso.tests.login.LoginUITestsK
import com.chesire.nekome.kaspresso.tests.main.MainActivityUITestsK
import com.chesire.nekome.kaspresso.tests.search.SearchUITestsK
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    MainActivityUITestsK::class,
    LoginUITestsK::class,
    SearchUITestsK::class,
)

class UITestsSuite