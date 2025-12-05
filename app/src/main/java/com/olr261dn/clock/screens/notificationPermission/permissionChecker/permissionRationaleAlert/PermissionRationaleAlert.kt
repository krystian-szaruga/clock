package com.olr261dn.clock.screens.notificationPermission.permissionChecker.permissionRationaleAlert

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.olr261dn.clock.R

@Composable
fun PermissionRationaleAlert(
    context: Context,
    hideAlert: () -> Unit) {
    AlertDialog(
        onDismissRequest = { hideAlert.invoke()},
        title = { Text(stringResource(id = R.string.permission_alert_title)) },
        text = { Text(stringResource(id = R.string.permission_alert_text)) },
        confirmButton = {
            Button(onClick = {
                hideAlert.invoke()
                context.startActivity(
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts(
                            "package", context.packageName, null)
                    }
                )
            }) {
                Text("Open Settings")
            }
        },
        dismissButton = {
            Button(onClick = { hideAlert.invoke() }) {
                Text("Cancel")
            }
        }
    )
}
