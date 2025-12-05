package com.olr261dn.clock.screens.notificationPermission

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.notificationPermission.permissionChecker.CheckNotificationPermission
import com.olr261dn.clock.screens.notificationPermission.permissionChecker.permissionRationaleAlert.PermissionRationaleAlert
import com.olr261dn.clock.utils.checkFullScreenIntentsPermission.checkAndRequestFullScreenIntentsPermission


@Composable
fun NotificationPermission(navigateToScreen: NavigateToScreen){
    var showPermissionRationale by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var isPermissionGranted by rememberSaveable { mutableStateOf(false) }

    CheckNotificationPermission(
        isPermissionGranted = isPermissionGranted,
        context = context,
        updatePermissionState = { isPermissionGranted = it },
        onPermissionGranted = {
            isPermissionGranted = true
            showPermissionRationale = false
        },
        onPermissionDenied = {
            showPermissionRationale = true
        }
    )
    LaunchedEffect(isPermissionGranted) {
        if (isPermissionGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                context.checkAndRequestFullScreenIntentsPermission()
            }
            navigateToScreen.navigateAndClearBackStack(ClockScreens.Clock.name)
        }
    }

    if (showPermissionRationale) PermissionRationaleAlert(context) {
        showPermissionRationale = false
    }
}
