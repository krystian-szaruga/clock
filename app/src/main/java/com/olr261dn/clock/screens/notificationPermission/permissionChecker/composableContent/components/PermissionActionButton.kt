package com.olr261dn.clock.screens.notificationPermission.permissionChecker.composableContent.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.buttons.CreateButton


@Composable
fun PermissionActionButton(
    closeApp: () -> Unit,
    launchPermissionManager: () -> Unit)
{
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer)

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CreateButton(
            text = "CLOSE APP", buttonColors = buttonColors) {
            closeApp.invoke() }

        Spacer(modifier = Modifier.width(8.dp))
        CreateButton(
            text = "OK", buttonColors = buttonColors) {
            launchPermissionManager.invoke() }
    }
}
