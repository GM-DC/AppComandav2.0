package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.SendOrdersApi
import com.example.appcomandav20.data.source.remote.dto.SendOrdersDTO
import com.example.appcomandav20.data.source.remote.dto.toSendOrdersModel
import com.example.appcomandav20.data.source.remote.response.OrderResponseDTO
import com.example.appcomandav20.data.source.remote.response.toOrderResponseModel
import com.example.appcomandav20.domain.model.OrderResponseModel
import com.example.appcomandav20.domain.repositories.SendOrdersRepository
import com.example.apppedido.domain.Model.SendOrdersModel
import com.example.apppedido.domain.Model.toSendOrdersDTO
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