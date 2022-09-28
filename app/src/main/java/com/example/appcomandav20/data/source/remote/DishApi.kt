package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.DishDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DishApi {
    @GET("/{nombrecategoria},{moneda}")
    suspend fun getDishes(@Path("nombrecategoria") nombrecategoria:String, @Path("moneda") moneda:String) : List<DishDTO>
}