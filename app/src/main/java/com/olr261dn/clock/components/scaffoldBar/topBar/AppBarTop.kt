package com.olr261dn.clock.components.scaffoldBar.topBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.olr261dn.clock.components.colors.appBar.getAppBarColor
import com.olr261dn.clock.components.colors.appBar.getOnAppBarColor
import com.olr261dn.clock.utils.fontSize.nonScaledSp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarTop(
    title: String = "Clock App",
    onReload: () -> Unit = {},
    onSettings: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Icon(
                    modifier = Modifier.clickable { onReload() },
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh Icon")
                Text(
                    text = title,
                    maxLines = 1,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 56.nonScaledSp,
                    color = getOnAppBarColor()
                )
                Icon(
                    modifier = Modifier.clickable { onSettings.invoke() },
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings Icon")
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = getAppBarColor(),
        )
    )
}
