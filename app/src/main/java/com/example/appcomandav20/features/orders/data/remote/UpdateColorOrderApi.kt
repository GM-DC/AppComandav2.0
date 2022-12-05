package com.example.appcomandav20.features.orders.data.remote

import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateColorOrderApi {

    @PUT("/api/Pedido/EstadoComandado/{comanda}/{idpedido}")
    suspend fun putUpdateColorOrder(@Path("comanda") comanda:String, @Path("idpedido") idpedido:Int) : Void

}