package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.domain.repository.UpdateStateTableRepository
import javax.inject.Inject

class PutUpdateStateTableUseCase @Inject constructor(
    private val updateStateTableRepository : UpdateStateTableRepository
){
    suspend operator fun invoke(idZona: String,idMesa: Int,estadoMesa: String,nameMozo: String): NetworkResult<Void> {
        return updateStateTableRepository.putUpdateStateTable(idZona,idMesa,estadoMesa,nameMozo)
    }
}