package com.example.appcomandav20.features.zones.data.remote


import com.example.appcomandav20.features.zones.data.remote.dto.TableDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TableApi {

    @GET("api/Mesas")
    suspend fun getTable(@Query("filter") filter:String) : List<TableDTO>
}