package com.olr261dn.clock.screens.timer.components.timerPicker.utils

import android.util.Log
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.utils.fontSize.nonScaledSp
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import java.util.Locale
import kotlin.math.abs

@Composable
fun WheelPicker(
    value: Int,
    range: IntRange,
    label: String,
    onSelected: (Int) -> Unit) {

    val rangeValues = listOf(
        range.last - 1, range.last
    ) +
            range.toList() +
            listOf(range.first, range.first + 1)
    val state = rememberLazyListState()
    var selectedIndex by remember { mutableIntStateOf(value) }
    LaunchedEffect(Unit) {
        state.scrollToItem(selectedIndex - 1)
    }

    val layoutInfo by remember { derivedStateOf { state.layoutInfo } }
    val visibleItemsInfo = layoutInfo.visibleItemsInfo

    val closestItemIndex by remember(visibleItemsInfo) {
        derivedStateOf {
            val viewportHeight = layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset
            val middleOfViewport = layoutInfo.viewportStartOffset + viewportHeight / 2

            visibleItemsInfo.minByOrNull { item ->
                abs((item.offset + item.size / 2) - middleOfViewport)
            }?.index ?: selectedIndex
        }
    }
    LaunchedEffect(closestItemIndex) {
        selectedIndex = closestItemIndex
    }

    UpdateSelectedItem(
        selectedIndex, rangeValues, state, range) {
        onSelected(it)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            modifier = Modifier.padding(3.dp),
            fontSize = 30.nonScaledSp
        )
        Box(
            modifier = Modifier
                .height(150.dp)
                .width(100.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                state = state,
                modifier = Modifier.fillMaxSize(),
                flingBehavior = rememberSnapFlingBehavior(state)
            ) {
                itemsIndexed(rangeValues) { idx, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val isSelected = idx == selectedIndex
                        Text(
                            text = String.format(Locale.US, "%02d", item),
                            fontSize = if (isSelected) 30.nonScaledSp else 24.nonScaledSp,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.ExtraLight,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UpdateSelectedItem(
    selectedIndex: Int,
    rangeValues: List<Int>,
    state: LazyListState,
    range: IntRange,
    onSelected: (Int) -> Unit
) {
    LaunchedEffect(selectedIndex) {
        val newItem = rangeValues.getOrNull(selectedIndex)
        if (newItem != null) {
            onSelected(newItem)
            try {
                when (selectedIndex) {
                    1 -> {
                        withTimeout(1200) {
                            state.requestScrollToItem(
                                range.last + 1
                            )
                        }
                    }

                    range.last + 3 -> {
                        withTimeout(1200) {
                            state.requestScrollToItem(
                                range.first + 1
                            )
                        }
                    }
                }
            } catch (e: TimeoutCancellationException) {
                Log.e(
                    "Wheel Picker",
                    "Scrolling operation timed out: ${e.message}"
                )
            }
        }
    }
}