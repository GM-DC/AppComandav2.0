package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.OrderModel
import com.example.appcomandav20.features.orders.domain.model.PreCount
import com.example.appcomandav20.features.orders.domain.repositories.OrderRepository
import com.example.appcomandav20.features.orders.domain.repositories.PrintPreCountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreCountUseCase @Inject constructor(
    private val printPreCountRepository : PrintPreCountRepository
) {
    operator fun invoke(idpedido:String): Flow<NetworkResult<PreCount>> {
        return printPreCountRepository.getOrder(idpedido)
    }

}