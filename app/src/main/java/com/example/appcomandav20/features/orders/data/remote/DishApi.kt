package com.example.appcomandav20.features.orders.data.remote

import com.example.appcomandav20.features.orders.data.remote.dto.DishDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DishApi {
    @GET("/{nombrecategoria},{moneda}")
    suspend fun getDishes(@Path("nombrecategoria") nombrecategoria:String, @Path("moneda") moneda:String) : List<DishDTO>
}