package com.example.appcomandav20.features.orders.data.remote

import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateStateTableApi {

    @PUT("/api/Mesas/EstadoMesa/{Piso}/{Mesa}/{Estado}/{Mozo}")
    suspend fun putCambiarEstadoMesa(@Path("Piso") idZona:String, @Path("Mesa") idMesa:Int, @Path("Estado") estadoMesa:String, @Path("Mozo") nameMozo:String) : Void

}