package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.SendOrdersDTO
import com.example.appcomandav20.data.source.remote.response.OrderResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SendOrdersApi {

    @POST("api/Pedido/CreateOrder")
    fun postSendOrders(@Body ordenPedido: SendOrdersDTO) : OrderResponseDTO

}