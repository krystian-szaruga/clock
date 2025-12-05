package com.olr261dn.clock.screens.alarm.components.handleAlarmState.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getButtonColors
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor

@Composable
fun DisplayErrorPopup(
    errorMsg: String,
    onRetry: () -> Unit) {

    var reloadApp by rememberSaveable {
        mutableStateOf(false) }
    if (reloadApp) { onRetry() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundColor()), // Semi-transparent background
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f), // Set width to 90% of the screen
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "An Error Occurred",
                    style = MaterialTheme.typography.headlineLarge,
                    color = getOnBackgroundColor(),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = errorMsg,
                    style = MaterialTheme.typography.bodyLarge,
                    color = getOnBackgroundColor(),
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "If the problem persists, please try reinstalling the app.",
                    style = MaterialTheme.typography.bodySmall,
                    color = getBackgroundColor(),
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        reloadApp = true
                    },
                    colors = getButtonColors()
                ) {
                    Text(text = "Retry")
                }

            }
        }
    }
}