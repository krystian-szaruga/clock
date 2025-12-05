package com.olr261dn.clock.screens.worldClocks.timeDisplayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.commonComposable.EmptyScreenMsg
import com.olr261dn.clock.model.Zone
import com.olr261dn.clock.screens.clock.viewModel.ActualTimeViewModel
import com.olr261dn.clock.screens.worldClocks.timeDisplayer.components.ClockItem
import com.olr261dn.clock.screens.worldClocks.timeDisplayer.components.DeleteAllClocks


@Composable
fun DisplayTime(
    zones: List<Zone>,
    onDeleteClock: (String) -> Unit,
    onPress: () -> Unit)
{
    Column(
        modifier = Modifier.padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (zones.isEmpty()) {
            EmptyScreenMsg("Press the '+' button to add a clock.")
        } else {
            DeleteAllClocks { onPress.invoke() }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(modifier = Modifier.fillMaxHeight(0.9f)) {
            items(zones) { zone ->
                val zoneTimeViewModel: ActualTimeViewModel = remember {
                    ActualTimeViewModel() }
                ClockItem(
                    zoneTimeViewModel = zoneTimeViewModel,
                    zoneId = zone.zoneId){ onDeleteClock.invoke(it) }
            }
        }
    }
}
