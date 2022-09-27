package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.dto.LoginUserDTO
import com.example.appcomandav20.data.source.remote.response.LoginUserResponse
import com.example.appcomandav20.domain.model.LoginUserModel
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginUserRepository {

    suspend fun postLoginUser(login: LoginUserModel): NetworkResult<LoginUserResponse>
}