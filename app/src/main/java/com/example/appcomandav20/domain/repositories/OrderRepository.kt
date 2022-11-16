package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrder(idPedido:String): Flow<NetworkResult<List<OrderModel>>>

}