package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.SendOrdersModel

interface SendOrdersRepository {

    suspend fun postSendOrders(orders: SendOrdersModel): NetworkResult<SendOrdersModel>

}