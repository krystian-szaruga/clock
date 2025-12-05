package com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.timeZonesProvider

import java.time.ZoneOffset

class TimeZoneFormatter {
    fun formatZoneOffsets(
        zoneOffsets: List<Pair<String, ZoneOffset>>): List<String> {
        return zoneOffsets
            .sortedByDescending { it.second }
            .map { (zoneId, offset) ->
                val offsetString = offset.toString().replace(
                    "Z", "00:00")
                "$zoneId, $offsetString"
            }
    }
}