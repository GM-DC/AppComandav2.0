package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.response.OrderResponseDTO
import com.example.appcomandav20.domain.model.OrderResponseModel
import com.example.apppedido.domain.Model.SendOrdersModel

interface SendOrdersRepository {

    suspend fun postSendOrders(orders: SendOrdersModel): NetworkResult<OrderResponseModel>

}