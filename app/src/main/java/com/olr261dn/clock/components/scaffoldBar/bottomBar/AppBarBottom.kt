package com.olr261dn.clock.components.scaffoldBar.bottomBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.R
import com.olr261dn.clock.components.colors.appBar.getAppBarColor
import com.olr261dn.clock.components.colors.appBar.getOnAppBarColor
import com.olr261dn.clock.components.colors.common.getOnBackgroundColor
import com.olr261dn.clock.navigation.ClockScreens
import com.olr261dn.clock.navigation.NavigateToScreen
import com.olr261dn.clock.utils.fontSize.nonScaledSp


@Composable
fun AppBarBottom(
    navigateToScreen: NavigateToScreen,
    initialScrollIndex: Int) {

    val listState = rememberLazyListState()

    LaunchedEffect(initialScrollIndex) {
        listState.scrollToItem(initialScrollIndex)
    }
    BottomAppBar(
        containerColor = getAppBarColor()
    ) {
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1f),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            state = listState
        ) {
            item {
                BottomAppBarItem(
                    icon = R.drawable.baseline_watch_24,
                    description = "Clock"
                ) {
                    navigateToScreen.navigateAndClearBackStack(ClockScreens.Clock.name)
                }
            }
            item {
                BottomAppBarItem(
                    icon = R.drawable.ic_world,
                    description = "World"
                ) {
                    navigateToScreen.navigateAndClearBackStack(ClockScreens.WorldClocks.name)
                }
            }
            item {
                BottomAppBarItem(
                    icon = R.drawable.baseline_alarm_24,
                    description = "Alarm"
                ) {
                    navigateToScreen.navigateAndClearBackStack(ClockScreens.Alarm.name)
                }
            }
            item {
                BottomAppBarItem(
                    icon = R.drawable.ic_hourglass,
                    description = "Timer"
                ) {
                    navigateToScreen.navigateAndClearBackStack(ClockScreens.Timer.name)
                }
            }
            item {
                BottomAppBarItem(
                    icon = R.drawable.ic_timer,
                    description = "Stopwatch"
                ) {
                    navigateToScreen.navigateAndClearBackStack(ClockScreens.Stopwatch.name)
                }
            }
        }
    }
}

@Composable
private fun BottomAppBarItem(
    icon: Int,
    description: String,
    navigateOnClick: () -> Unit) {

    Card(
        shape = RectangleShape,
        modifier = Modifier.wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = getOnAppBarColor()
        ),
        onClick = { navigateOnClick.invoke() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                tint = getOnBackgroundColor(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(34.dp),
                painter = painterResource(id = icon),
                contentDescription = description)
            Text(
                text = description,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 18.nonScaledSp)
            Spacer(modifier = Modifier.width(60.dp))
        }
    }
}
