package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.OrderModel
import com.example.appcomandav20.domain.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository : OrderRepository
) {

    operator fun invoke(idpedido:String): Flow<NetworkResult<List<OrderModel>>> {
        return orderRepository.getOrder(idpedido)
    }

}
