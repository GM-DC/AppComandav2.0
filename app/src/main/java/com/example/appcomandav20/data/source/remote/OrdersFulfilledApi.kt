package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.OrdersFulfilledDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface OrdersFulfilledApi {

    @GET("/api/Pedido/PedidoMesa/{id}")
    suspend fun getOrdersFulfilled(@Path("id") id:String) : List<OrdersFulfilledDTO>

}