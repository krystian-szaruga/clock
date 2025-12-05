package com.olr261dn.clock.screens.worldClocks.timeDisplayer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.components.surfaceWithContent.SurfaceWithColumn
import com.olr261dn.clock.screens.clock.viewModel.ActualTimeViewModel
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import com.olr261dn.clock.viewModels.GlobalSettingsViewModel


@Composable
fun ClockItem(
    zoneTimeViewModel: ActualTimeViewModel,
    globalSettingsViewModel: GlobalSettingsViewModel = hiltViewModel(),
    zoneId: String,
    onDeleteClock: (String) -> Unit) {

    val zoneName = zoneId.split(",")[0]
    val (region, city) = zoneName.split("/")
    val actualTime = zoneTimeViewModel.currentTime.collectAsState()
    val timeFormat = globalSettingsViewModel.timeFormat.collectAsState()
    zoneTimeViewModel.startTime(timeFormat.value, zoneName = zoneName)

    LaunchedEffect(timeFormat) {
        globalSettingsViewModel.initialize()
    }

    SurfaceWithColumn{
        IconButton(
            onClick = { onDeleteClock.invoke(zoneId) }) {
            Icon(
                tint = getOnBackgroundColor(),
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Clock")
        }
        LazyRow (
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            item {
                RegionTime(
                    actualTime = actualTime.value,
                    region = region,
                    city = city
                )
            }
        }
    }
}

@Composable
private fun RegionTime(
    actualTime: String,
    region: String,
    city: String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            color = getOnBackgroundColor(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start,
            fontSize = 30.nonScaledSp,
            text = "$region, $city")
        Spacer(Modifier.height(20.dp))
        Text(
            color = getOnBackgroundColor(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End,
            fontSize = 30.nonScaledSp,
            text = actualTime
        )
    }
}
