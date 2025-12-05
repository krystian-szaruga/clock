package com.olr261dn.clock.screens.worldClocks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olr261dn.clock.components.colors.common.getBackgroundColor
import com.olr261dn.clock.components.scaffoldBar.ScaffoldBar
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.screens.worldClocks.timeDisplayer.DisplayTime
import com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.TimeZoneGetterViewModel
import com.olr261dn.clock.screens.worldClocks.viewModels.zonesViewModel.ZoneViewModel
import com.olr261dn.clock.screens.worldClocks.zoneSelector.ZoneSelector


@Composable
fun WorldClocks(
    timeZoneGetterViewModel: TimeZoneGetterViewModel = viewModel(),
    zoneViewModel: ZoneViewModel = hiltViewModel(),
    navigateToScreen: NavigateToScreen) {

    val zones = zoneViewModel.zones.collectAsState()
    val showPopup = rememberSaveable { mutableStateOf(false) }

    ScaffoldBar(
        title = "World",
        navigateToScreen = navigateToScreen,
        initialScrollIndex = 1,
        fabIcon = if (showPopup.value) Icons.Default.Done else Icons.Default.Add,
        fabContentOnTap = {
            showPopup.value = !showPopup.value
            if (showPopup.value) {
                timeZoneGetterViewModel.fetchTimeZoneOffsets() }
        })
    { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = getBackgroundColor())
        {
            if (showPopup.value) {
                ZoneSelector(
                    timeZoneGetterViewModel = timeZoneGetterViewModel,
                    zoneViewModel = zoneViewModel,
                    zones = zones.value)
            } else {
                DisplayTime(
                    zones = zones.value,
                    onDeleteClock = {
                        zoneViewModel.deleteZone(it)
                    }) {
                    zoneViewModel.deleteAll()
                }
            }
        }
    }
}
