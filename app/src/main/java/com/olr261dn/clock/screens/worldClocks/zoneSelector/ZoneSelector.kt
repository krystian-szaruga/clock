package com.olr261dn.clock.screens.worldClocks.zoneSelector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.model.Zone
import com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.TimeZoneGetterViewModel
import com.olr261dn.clock.screens.worldClocks.viewModels.zonesViewModel.ZoneViewModel
import com.olr261dn.clock.screens.worldClocks.zoneSelector.components.searchForm.SearchForm
import com.olr261dn.clock.screens.worldClocks.zoneSelector.components.zoneItem.ZoneItem

@Composable
fun ZoneSelector(
    zones: List<Zone>,
    timeZoneGetterViewModel: TimeZoneGetterViewModel,
    zoneViewModel: ZoneViewModel) {

    val zonesOffsets = timeZoneGetterViewModel.filteredTimeZoneOffsets.collectAsState()
    val isLoading = timeZoneGetterViewModel.isLoading.collectAsState()

    Box(modifier = Modifier.background(getBackgroundColor()))
    if (isLoading.value) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = getOnBackgroundColor()
        )
    } else {
        Column {
            SearchForm { timeZoneGetterViewModel.filterTimeZoneOffsets(it) }
            Surface(
                color = getBackgroundColor(),
                shape = RoundedCornerShape(20.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(zonesOffsets.value) { zoneId ->
                        ZoneItem(
                            zoneId = zoneId,
                            zones = zones)
                        { isChecked ->
                            if (isChecked) {
                                zoneViewModel.addZone(zoneId)
                            } else {
                                zoneViewModel.deleteZone(zoneId)
                            }
                        }
                    }
                }
            }
        }
    }
}




