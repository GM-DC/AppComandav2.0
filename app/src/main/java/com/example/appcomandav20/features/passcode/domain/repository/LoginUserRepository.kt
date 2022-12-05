package com.example.appcomandav20.features.passcode.domain.repository

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.core.db.entity.EntityLoginExito
import com.example.appcomandav20.features.passcode.domain.model.LoginUserModel
import com.example.appcomandav20.features.passcode.domain.model.LoginUserResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginUserRepository {

    suspend fun postLoginUser(login: LoginUserModel): NetworkResult<LoginUserResponseModel>

    fun getLoginUserResponseFromDatabase(): Flow<EntityLoginExito>

    suspend fun insertLoginUserResponseFromDatabase(loginReponseModel: EntityLoginExito)

    suspend fun deleteLoginUserResponseFromDatabase()

    suspend fun cleanPrimaryKeyLoginUserResponseFromDatabase()

}