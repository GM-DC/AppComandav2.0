package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.repositories.UpdateColorOrderRespository
import javax.inject.Inject

class PutUpdateColorOrderUseCase @Inject constructor(
    private val updateColorOrderRespository : UpdateColorOrderRespository
){
    suspend operator fun invoke(comanda:String, idpedido:Int): NetworkResult<Void> {
        return updateColorOrderRespository.putUpdateColorOrder(comanda,idpedido)
    }
}