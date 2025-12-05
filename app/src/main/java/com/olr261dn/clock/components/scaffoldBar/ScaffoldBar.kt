package com.olr261dn.clock.components.scaffoldBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.olr261dn.clock.components.scaffoldBar.bottomBar.AppBarBottom
import com.olr261dn.clock.components.scaffoldBar.fabContent.FabContent
import com.olr261dn.clock.components.scaffoldBar.notificationPermissionChecker.NotificationPermissionChecker
import com.olr261dn.clock.components.scaffoldBar.topBar.AppBarTop
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.navigation.NavigateToScreen


@Composable
fun ScaffoldBar(
    navigateToScreen: NavigateToScreen,
    title: String = "ClockApp",
    fabContentOnTap: (() -> Unit)? = null,
    fabIcon: ImageVector = Icons.Default.Add,
    initialScrollIndex: Int = 0,
    composableContent: @Composable (PaddingValues) -> Unit
) {
    NotificationPermissionChecker(LocalContext.current, navigateToScreen)
    Scaffold(
        topBar = { AppBarTop(
            title=title,
            onReload = { navigateToScreen.navigateAndClearBackStack(null) }) {
            navigateToScreen.navigateWithoutClearingBackStack(ClockScreens.Settings.name)
        } },
        floatingActionButton = { if (fabContentOnTap != null) FabContent (
            fabIcon = fabIcon){ fabContentOnTap.invoke() } },
        bottomBar = { AppBarBottom(navigateToScreen, initialScrollIndex) }) {
        composableContent(it)
    }
}
