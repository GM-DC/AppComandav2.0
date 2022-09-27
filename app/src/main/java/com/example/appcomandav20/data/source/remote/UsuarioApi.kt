package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.UsuarioDTO
import retrofit2.http.GET

interface UsuarioApi {

    @GET("/api/Users?select=nombre,codigo")
    suspend fun getUsuarios(): List<UsuarioDTO>
}