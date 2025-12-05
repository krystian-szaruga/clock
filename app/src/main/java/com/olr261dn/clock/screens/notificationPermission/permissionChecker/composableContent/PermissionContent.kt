package com.olr261dn.clock.screens.notificationPermission.permissionChecker.composableContent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.screens.notificationPermission.permissionChecker.composableContent.components.PermissionActionButton
import com.olr261dn.clock.screens.notificationPermission.permissionChecker.composableContent.components.PermissionNeededAlert

@Composable
fun PermissionContent(
    closeApp: () -> Unit,
    launchPermissionManager: () -> Unit)
{
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background)
    {
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            item {
                Card(
                    modifier = Modifier.padding(16.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiaryContainer),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                        MaterialTheme.colorScheme.secondaryContainer.copy(
                            0.7f
                        ),
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    PermissionNeededAlert()
                }
            }
            item {
                PermissionActionButton(closeApp = { closeApp.invoke() }) {
                    launchPermissionManager.invoke()
                }
            }

        }
    }
}
