package com.olr261dn.clock.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

class NavigateToScreen(
    private val navController: NavController,
    private val currentScreen: String,
    private val updateLastScreen: (String) -> Unit)
{
    private var screenToNavigate = currentScreen

    fun navigateAndClearBackStack(destinationScreen: String? = null){
        setDestinationScreen(destinationScreen)
        navigateToScreen {
            popUpTo(currentScreen) { inclusive = true }
            launchSingleTop = true
        }
    }

    fun navigateWithoutClearingBackStack(destinationScreens: String? = null) {
        setDestinationScreen(destinationScreens)
        navigateToScreen {
            launchSingleTop = true
        }
    }

    private fun setDestinationScreen(destinationScreens: String?) {
        if (destinationScreens != null) screenToNavigate = destinationScreens
        updateLastScreen(screenToNavigate)
    }

    private fun navigateToScreen(builder: NavOptionsBuilder.() -> Unit){
        navController.navigate(screenToNavigate) {
            builder()
        }
    }
}
