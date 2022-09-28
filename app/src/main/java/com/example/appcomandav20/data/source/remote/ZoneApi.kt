package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.ZoneDTO
import retrofit2.Response
import retrofit2.http.GET

interface ZoneApi {

    @GET("api/TablasBasicas/Detail?filter=codigo eq 'CDG_PISO'&select=nombre,numero")
    suspend fun getZones(): List<ZoneDTO>
}