package com.example.appcomandav20.features.zones.domain.repository

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.domain.model.ZoneModel
import kotlinx.coroutines.flow.Flow

interface ZonesRepository {
    fun getZones(): Flow<NetworkResult<List<ZoneModel>>>
}