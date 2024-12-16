package com.chesire.nekome.kaspresso.tests

import com.chesire.nekome.kaspresso.screens.mainScreen
import com.chesire.nekome.kaspresso.screens.settingsScreen
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class SettingsTestsK : BaseTestK() {

    @Test
    fun canReachSettings() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            assert {
                isOnScreen()
            }
        }
    }

    @Test
    fun changeDefaultSeriesState() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            clickDefaultSeriesState()

            defaultSeriesState {
                assert {
                    isLoadedCorrectly()
                }
            }

            defaultSeriesState {
                chooseCurrent()

                assert {
                    currentIsSelected()
                }

                confirm()
            }

            clickDefaultSeriesState()

            defaultSeriesState {
                chooseCompleted()

                assert {
                    completedIsSelected()
                }

                confirm()
            }

            clickDefaultSeriesState()

            defaultSeriesState {
                chooseOnHold()

                assert {
                    onHoldIsSelected()
                }

                confirm()
            }

            clickDefaultSeriesState()

            defaultSeriesState {
                chooseDropped()

                assert {
                    droppedIsSelected()
                }

                confirm()
            }

            clickDefaultSeriesState()

            defaultSeriesState {
                choosePlanned()

                assert {
                    plannedIsSelected()
                }

                confirm()
            }
        }
    }

    @Test
    fun changeDefaultHomeScreenState() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            clickDefaultHomeScreen()

            defaultHomeScreen {
                assert {
                    isLoadedCorrectly()
                }
            }

            defaultHomeScreen {
                chooseAnime()

                assert {
                    animeIsSelected()
                }

                confirm()
            }

            clickDefaultHomeScreen()

            defaultHomeScreen {
                chooseManga()

                assert {
                    mangaIsSelected()
                }

                confirm()
            }
        }
    }

    @Test
    fun changeImageQuality() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            clickImageQuality()

            imageQuality {
                assert {
                    isLoadedCorrectly()
                }
            }

            imageQuality {
                chooseHigh()

                assert {
                    highIsSelected()
                }

                confirm()
            }

            clickImageQuality()

            imageQuality {
                assert {
                    highIsSelected()
                }

                chooseMedium()

                assert {
                    mediumIsSelected()
                }

                confirm()
            }
        }
    }

    @Test
    fun changeTitleLanguage() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            clickTitleLanguage()

            titleLanguage {
                assert {
                    isLoadedCorrectly()
                }
            }

            titleLanguage {
                chooseRomaji()

                assert {
                    romajiIsSelected()
                }

                confirm()
            }

            clickTitleLanguage()

            titleLanguage {
                assert {
                    romajiIsSelected()
                }

                chooseJapanese()

                assert {
                    japaneseIsSelected()
                }

                confirm()
            }
        }
    }

    @Test
    fun changeThemeSetting() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            clickTheme()

            changeTheme {
                assert {
                    isLoadedCorrectly()
                }
            }

            changeTheme {
                chooseDark()

                assert {
                    darkIsSelected()
                }

                confirm()
            }

            clickTheme()

            changeTheme {
                chooseLight()

                assert {
                    lightIsSelected()
                }

                confirm()
            }

            clickTheme()

            changeTheme {
                chooseSystem()

                assert {
                    systemIsSelected()
                }

                confirm()
            }
        }
    }

    @Test
    fun changeRateOnCompletionSetting() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            changeRateOnComplete()
        }
    }

    @Test
    fun openOSS() {
        launchActivity()

        mainScreen {
            goToSettings()
        }

        settingsScreen {
            goToLicenses()
        }
    }
}
