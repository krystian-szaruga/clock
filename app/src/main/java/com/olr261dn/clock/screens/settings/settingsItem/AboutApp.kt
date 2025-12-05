package com.olr261dn.clock.screens.settings.settingsItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.R
import com.olr261dn.clock.components.colors.common.getButtonColors

@Composable
fun AboutApp() {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            colors = getButtonColors()) {
            Text(text = "About this App")
        }
    }
    if (showDialog) {
        AboutAppDialog(onDismiss = { showDialog = false })
    }
}

@Composable
private fun AboutAppDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "About this App") },
        text = {Text(text = stringResource(R.string.about_app_description))},
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = getButtonColors()) {
                Text("Close")
            }
        }
    )
}