package com.example.appcomandav20.features.passcode.data.remote

import com.example.appcomandav20.features.orders.data.remote.dto.LoginUserDTO
import com.example.appcomandav20.features.passcode.data.remote.dto.LoginUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginApi {

    @POST("api/Users/Login")
    suspend fun postLoginComanda(@Body loginMozo: LoginUserDTO) : LoginUserResponse

}