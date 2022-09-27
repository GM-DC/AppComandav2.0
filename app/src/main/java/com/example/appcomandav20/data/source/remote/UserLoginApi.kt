package com.example.appcomandav20.data.source.remote

import com.example.appcomandav20.data.source.remote.dto.LoginUserDTO
import com.example.appcomandav20.data.source.remote.response.LoginUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginApi {

    @POST("api/Users/Login")
    suspend fun postLoginComanda(@Body loginMozo: LoginUserDTO) : LoginUserResponse

}