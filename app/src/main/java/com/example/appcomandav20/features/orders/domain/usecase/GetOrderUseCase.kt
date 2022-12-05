package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.OrderModel
import com.example.appcomandav20.features.orders.domain.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository : OrderRepository
) {

    operator fun invoke(idpedido:String): Flow<NetworkResult<List<OrderModel>>> {
        return orderRepository.getOrder(idpedido)
    }

}
