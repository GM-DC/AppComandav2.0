package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.repositories.UpdateColorOrderRespository
import com.example.appcomandav20.domain.repositories.UpdateStateTableRepository
import javax.inject.Inject

class PutUpdateColorOrderUseCase @Inject constructor(
    private val updateColorOrderRespository : UpdateColorOrderRespository
){
    suspend operator fun invoke(comanda:String, idpedido:Int): NetworkResult<Void> {
        return updateColorOrderRespository.putUpdateColorOrder(comanda,idpedido)
    }
}