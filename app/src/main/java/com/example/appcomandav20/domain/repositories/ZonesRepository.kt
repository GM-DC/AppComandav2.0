package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.model.ZoneModel
import kotlinx.coroutines.flow.Flow

interface ZonesRepository {
    fun getZones(): Flow<NetworkResult<List<ZoneModel>>>
}