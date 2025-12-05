package com.olr261dn.clock.utils.checkFullScreenIntentsPermission

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.checkAndRequestFullScreenIntentsPermission() {
    if (checkFullScreenIntentsPermission()) {
        requestFullScreenIntentsPermission()
    }
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
private fun Context.checkFullScreenIntentsPermission(): Boolean =
    (getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)
        ?.canUseFullScreenIntent() == false


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.requestFullScreenIntentsPermission() {
    startActivity(Intent(Settings.ACTION_MANAGE_APP_USE_FULL_SCREEN_INTENT)
        .setData(Uri.fromParts("package", packageName, null)))
}

