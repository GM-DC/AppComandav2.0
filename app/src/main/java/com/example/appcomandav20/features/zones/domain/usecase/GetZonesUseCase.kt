package com.example.appcomandav20.features.zones.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.domain.model.ZoneModel
import com.example.appcomandav20.features.zones.domain.repository.ZonesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val zonesRepository : ZonesRepository
){
    operator fun invoke(): Flow<NetworkResult<List<ZoneModel>>> {
        return zonesRepository.getZones()
    }
}