package com.example.appcomandav20.features.orders.data.remote

import com.example.appcomandav20.features.orders.data.remote.dto.SendOrdersDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SendOrdersApi {

    @POST("api/Pedido/CreateOrder")
    suspend fun postSendOrders(@Body ordenPedido: SendOrdersDTO) : SendOrdersDTO

}