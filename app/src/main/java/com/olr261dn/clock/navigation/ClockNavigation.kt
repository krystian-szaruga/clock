package com.olr261dn.clock.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.olr261dn.clock.screens.alarm.Alarm
import com.olr261dn.clock.screens.clock.Clock
import com.olr261dn.clock.screens.notificationPermission.NotificationPermission
import com.olr261dn.clock.screens.settings.Settings
import com.olr261dn.clock.screens.splash.Splash
import com.olr261dn.clock.screens.stopwatch.Stopwatch
import com.olr261dn.clock.screens.timer.Timer
import com.olr261dn.clock.screens.worldClocks.WorldClocks
import com.olr261dn.clock.viewModels.UserSettingsViewModel

@Composable
fun ClockNavigation(
    userSettingsViewModel: UserSettingsViewModel = hiltViewModel(),
    screen: String?) {

    CloseApplication()
    val lastScreen by userSettingsViewModel.lastScreenName.collectAsState()
    val navController = rememberNavController()
    val navigateToScreen = NavigateToScreen(
        navController = navController,
        currentScreen = navController.currentBackStackEntryAsState()
            .value?.destination?.route ?: ClockScreens.Clock.name,
        updateLastScreen = {
            userSettingsViewModel.updateLastScreen(it)
        })

    NavHost(
        navController = navController,
        startDestination = screen ?: lastScreen ?: ClockScreens.Splash.name
    ) {
        composable(ClockScreens.Splash.name) {
            Splash()
        }

        composable(ClockScreens.NotificationPermission.name) {
            NotificationPermission(navigateToScreen = navigateToScreen)
        }

        composable(ClockScreens.Clock.name) {
            Clock(navigateToScreen = navigateToScreen)
        }

        composable(ClockScreens.WorldClocks.name) {
            WorldClocks(navigateToScreen = navigateToScreen)
        }

        composable(ClockScreens.Alarm.name) {
            Alarm(navigateToScreen = navigateToScreen)
        }

        composable(ClockScreens.Stopwatch.name) {
            Stopwatch(navigateToScreen = navigateToScreen)
        }

        composable(ClockScreens.Settings.name) {
            Settings(navigateToScreen = navigateToScreen)
        }

        composable(ClockScreens.Timer.name) {
            Timer(navigateToScreen = navigateToScreen)
        }
    }
}


@Composable
fun CloseApplication() {
    val context = LocalContext.current

    BackHandler {
        (context as? Activity)?.finish()
    }
}