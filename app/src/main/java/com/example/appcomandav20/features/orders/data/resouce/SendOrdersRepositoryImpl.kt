package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.data.remote.SendOrdersApi
import com.example.appcomandav20.features.orders.data.remote.dto.toSendOrdersModel
import com.example.appcomandav20.features.orders.domain.repositories.SendOrdersRepository
import com.example.appcomandav20.features.orders.domain.model.SendOrdersModel
import com.example.appcomandav20.features.orders.domain.model.toSendOrdersDTO
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class SendOrdersRepositoryImpl @Inject constructor(
    private val api: SendOrdersApi
): SendOrdersRepository {
    override suspend fun postSendOrders(orders: SendOrdersModel): NetworkResult<SendOrdersModel> {
        try {
            val response = api.postSendOrders(orders.toSendOrdersDTO())
            return NetworkResult.Success(data = response.toSendOrdersModel())
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
    }
}