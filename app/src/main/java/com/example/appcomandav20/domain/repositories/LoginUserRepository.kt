package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.database.entity.EntityLoginExito
import com.example.appcomandav20.domain.model.LoginUserModel
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginUserRepository {

    suspend fun postLoginUser(login: LoginUserModel): NetworkResult<LoginUserResponseModel>

    fun getLoginUserResponseFromDatabase(): Flow<EntityLoginExito>

    suspend fun insertLoginUserResponseFromDatabase(loginReponseModel: EntityLoginExito)

    suspend fun deleteLoginUserResponseFromDatabase()

    suspend fun cleanPrimaryKeyLoginUserResponseFromDatabase()

}