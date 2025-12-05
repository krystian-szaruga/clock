package com.olr261dn.clock.utils.checkNotificationPermission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

fun checkNotificationPermission(context: Context): Boolean {
    return when {
        Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU -> true
        else -> ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }
}
