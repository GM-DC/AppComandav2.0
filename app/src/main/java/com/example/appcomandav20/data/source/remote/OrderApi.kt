package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.OrderDTO
import retrofit2.http.GET
import retrofit2.http.Query



interface OrderApi {

    @GET("api/Pedido/Comanda")
    suspend fun getComanda(@Query("idPedido") idPedido:String) : List<OrderDTO>

}