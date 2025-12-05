package com.olr261dn.clock.components.scaffoldBar.notificationPermissionChecker

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.utils.checkNotificationPermission.checkNotificationPermission
import kotlinx.coroutines.launch

@Composable
fun NotificationPermissionChecker(
    context: Context,
    navigateToScreen: NavigateToScreen) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            if (!checkNotificationPermission(context)) {
                navigateToScreen.navigateAndClearBackStack(ClockScreens.NotificationPermission.name)
            }
        }
    }
}