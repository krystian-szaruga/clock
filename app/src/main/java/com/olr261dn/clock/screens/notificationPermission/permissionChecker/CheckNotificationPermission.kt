package com.olr261dn.clock.screens.notificationPermission.permissionChecker

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.olr261dn.clock.screens.notificationPermission.permissionChecker.composableContent.PermissionContent
import com.olr261dn.clock.utils.checkNotificationPermission.checkNotificationPermission

@Composable
fun CheckNotificationPermission(
    isPermissionGranted: Boolean,
    context: Context,
    updatePermissionState: (Boolean) -> Unit,
    onPermissionGranted: () -> Unit,
    onPermissionDenied:  () -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationPermission = Manifest.permission.POST_NOTIFICATIONS
        val activity = context as Activity
        val notificationPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }
        PermissionContent (
            closeApp = {activity.finish()},
            launchPermissionManager = {
                notificationPermissionLauncher.launch(
                    notificationPermission)
            }
        )
        checkNotificationPermission(context)
        LaunchedEffect(Unit) {
            checkNotificationPermission(context)
            if (!isPermissionGranted) {
                notificationPermissionLauncher.launch(
                    notificationPermission)
            } else if (isPermissionGranted) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }
    } else {
        updatePermissionState(true)
    }
}