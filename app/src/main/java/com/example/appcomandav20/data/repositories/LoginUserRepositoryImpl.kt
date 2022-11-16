package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.UserLoginApi
import com.example.appcomandav20.data.source.remote.response.toLoginUserResponseModel
import com.example.appcomandav20.domain.database.dao.DaoLoginExito
import com.example.appcomandav20.domain.database.entity.EntityLoginExito
import com.example.appcomandav20.domain.model.LoginUserModel
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import com.example.appcomandav20.domain.model.toEntityLoginExito
import com.example.appcomandav20.domain.model.toLoginUserDTO
import com.example.appcomandav20.domain.repositories.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(
    private val api: UserLoginApi,
    private val dao: DaoLoginExito,
): LoginUserRepository{
    override suspend fun postLoginUser(login :LoginUserModel): NetworkResult<LoginUserResponseModel>{
        val response = try {
            api.postLoginComanda(loginMozo = login.toLoginUserDTO()).toLoginUserResponseModel()
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
                message = "Un error desconocido ocurrió",
                data = null
            )
        }
        deleteLoginUserResponseFromDatabase()
        cleanPrimaryKeyLoginUserResponseFromDatabase()
        insertLoginUserResponseFromDatabase(response.toEntityLoginExito())
        return NetworkResult.Success(data = response)
    }

    override fun getLoginUserResponseFromDatabase(): Flow<EntityLoginExito> {
        return dao.getAll()
    }

    override suspend fun insertLoginUserResponseFromDatabase(loginReponseModel: EntityLoginExito) {
        dao.insert(loginReponseModel)
    }

    override suspend fun deleteLoginUserResponseFromDatabase() {
        dao.deleteTable()
    }

    override suspend fun cleanPrimaryKeyLoginUserResponseFromDatabase() {
        dao.clearPrimaryKey()
    }
}