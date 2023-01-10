package com.example.appcomandav20.features.orders.data.remote

import com.example.appcomandav20.features.orders.data.remote.dto.PreCountDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PreCountApi {

    @GET("api/Pedido/Precuenta")
    suspend fun getPreCuenta(@Query("idPedido") idPedido:String) : PreCountDTO

}