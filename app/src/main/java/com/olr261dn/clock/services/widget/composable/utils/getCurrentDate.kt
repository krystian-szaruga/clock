package com.olr261dn.clock.services.widget.composable.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String {
    val datePattern = "EEE, dd MMM yyyy"
    val sdf = SimpleDateFormat(datePattern, Locale.getDefault())
    return sdf.format(Date())
}