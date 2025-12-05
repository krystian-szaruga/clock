package com.olr261dn.clock.screens.worldClocks.zoneSelector.components.zoneItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.olr261dn.clock.components.colors.common.getSecondBackgroundColor
import com.olr261dn.clock.model.Zone

@Composable
fun ZoneItem(
    zoneId: String,
    zones: List<Zone>,
    onCheckChanged: (Boolean) -> Unit)
{
    val enabled = remember { mutableStateOf(false) }
    enabled.value = setEnabled(zones, zoneId)
    val (location, timeDiff) = zoneId.split(",")
    val (region, area) = location.split("/")

    Surface(
        color = getSecondBackgroundColor(),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            item {
                Checkbox(
                    checked = enabled.value,
                    onCheckedChange = { isChecked ->
                        enabled.value = isChecked
                        onCheckChanged.invoke(isChecked)
                    })
            }
            item {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "${trimString(area)}," +
                            " ${trimString(region)}," +
                            " ${trimString(timeDiff)}",
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

private fun setEnabled(zones: List<Zone>, zoneId: String): Boolean =
    zones.any { it.zoneId == zoneId }


private fun trimString(value: String): String = value.trim()