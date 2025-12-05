package com.olr261dn.clock.repository

import com.olr261dn.clock.data.timeZone.ZoneDatabaseDao
import com.olr261dn.clock.model.Zone
import javax.inject.Inject

class ZoneRepository @Inject constructor(
    private val zoneDatabaseDao: ZoneDatabaseDao)
{

    suspend fun getZones() = zoneDatabaseDao.getZones()

    suspend fun insertZone(zone: Zone) =
        zoneDatabaseDao.insertZone(zone = zone)

    suspend fun deleteZone(zone: Zone) =
        zoneDatabaseDao.deleteZone(zone = zone)

    suspend fun deleteAll() {
        zoneDatabaseDao.deleteAll()
    }

}