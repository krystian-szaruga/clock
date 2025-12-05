package com.olr261dn.clock.screens.worldClocks.viewModels.timeZoneGetterViewModel.timeZonesProvider

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant

class TimeZoneOffsetProvider(
    private val fetcher: TimeZoneFetcher,
    private val formatter: TimeZoneFormatter
) {
    suspend fun getTimeZoneOffsets(): List<String> = withContext(Dispatchers.Default) {
        val now = Instant.now()
        val zoneOffsets = fetcher.fetchZoneOffsets(now)
        formatter.formatZoneOffsets(zoneOffsets)
    }
}