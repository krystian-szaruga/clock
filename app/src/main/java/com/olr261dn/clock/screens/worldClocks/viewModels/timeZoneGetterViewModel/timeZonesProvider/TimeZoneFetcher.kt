package com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.timeZonesProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

class TimeZoneFetcher {

    suspend fun fetchZoneOffsets(
        now: Instant): List<Pair<String, ZoneOffset>> =
        withContext(Dispatchers.Default) {
            ZoneId.getAvailableZoneIds()
                .filterNot {
                    it.contains("SystemV") ||
                    (it.split(",").size != 2 && it.split("/").size != 2) }
                .map { zoneId ->
                    val offset = ZonedDateTime.ofInstant(
                        now, ZoneId.of(zoneId)).offset
                    zoneId to offset
                }
        }
}

