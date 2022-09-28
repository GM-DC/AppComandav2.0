package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.model.ZoneModel
import com.example.appcomandav20.domain.repositories.UsuarioRepository
import com.example.appcomandav20.domain.repositories.ZonesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val zonesRepository : ZonesRepository
){
    operator fun invoke(): Flow<NetworkResult<List<ZoneModel>>> {
        return zonesRepository.getZones()
    }
}