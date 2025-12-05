package com.olr261dn.clock.screens.alarm.components.handleAlarmState.alarm.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.R
import com.olr261dn.clock.components.colors.common.getIconButtonColors
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithRow

@SuppressLint("InvalidColorHexValue")
@Composable
fun LongClickOption(
    totalElem: Int = 5,
    selectedElem: Int = 0,
    onSelectAll: () -> Unit,
    onCancelClick: () -> Unit,
    onDeleteClick: () -> Unit) {

    val iconRes = if (selectedElem == totalElem) {
        R.drawable.baseline_remove_done_24
    } else {
        R.drawable.baseline_done_all_24
    }
    SurfaceWithRow(
        roundedCornerDp = 2.dp,
        customBackgroundColor = getSecondBackgroundColor(),
        composableContent = {
            IconButton(
                colors = getIconButtonColors(),
                onClick = onCancelClick
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                    contentDescription = "Cancel",
                    modifier = Modifier.size(24.dp)
                )
            }
            Surface(
                modifier = Modifier.size(40.dp),
                color = Color.Transparent
            ) {
                IconButton(
                    colors = getIconButtonColors(),
                    onClick = { onSelectAll() })
                {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = "Select/Deselect All",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            text = "$selectedElem/$totalElem"
                        )
                    }
                }
            }
            IconButton(
                enabled = selectedElem > 0,
                colors = getIconButtonColors(),
                onClick = onDeleteClick
            )
            {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Alarm",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    )
}