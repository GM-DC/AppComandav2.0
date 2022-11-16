package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.repositories.SendOrdersRepository
import com.example.appcomandav20.domain.repositories.UpdateStateTableRepository
import com.example.apppedido.domain.Model.SendOrdersModel
import javax.inject.Inject

class PutUpdateStateTableUseCase @Inject constructor(
    private val updateStateTableRepository : UpdateStateTableRepository
){
    suspend operator fun invoke(idZona: String,idMesa: Int,estadoMesa: String,nameMozo: String): NetworkResult<Void> {
        return updateStateTableRepository.putUpdateStateTable(idZona,idMesa,estadoMesa,nameMozo)
    }
}