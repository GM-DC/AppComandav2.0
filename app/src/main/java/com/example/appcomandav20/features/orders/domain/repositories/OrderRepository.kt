package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrder(idPedido:String): Flow<NetworkResult<List<OrderModel>>>

}