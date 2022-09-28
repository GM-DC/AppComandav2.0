package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.UserLoginApi
import com.example.appcomandav20.data.source.remote.dto.LoginUserDTO
import com.example.appcomandav20.data.source.remote.dto.toUsuarioDC
import com.example.appcomandav20.data.source.remote.response.LoginUserResponse
import com.example.appcomandav20.data.source.remote.response.toLoginUserResponseModel
import com.example.appcomandav20.domain.model.LoginUserModel
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.model.toLoginUserDTO
import com.example.appcomandav20.domain.repositories.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(
    private val api: UserLoginApi
): LoginUserRepository{
    override suspend fun postLoginUser(login :LoginUserModel): NetworkResult<LoginUserResponse>{
        val response = try {
            api.postLoginComanda(loginMozo = login.toLoginUserDTO())
        }catch (e: HttpException) {
            return NetworkResult.Error(
                message = "Huy! Algo salió mal // Code: ${e.code()}",
                data = null
            )
        }catch (e: IOException) {
            return NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet // ${e.message}",
                data = null
            )
        }catch (e: Exception) {
            return NetworkResult.Error(
                message = "Un error desconocido ocurrió = ${e.message}",
                data = null
            )
        }


        return NetworkResult.Success(data = response)
    }
}