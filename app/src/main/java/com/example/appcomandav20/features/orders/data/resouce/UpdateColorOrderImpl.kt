package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.data.remote.UpdateColorOrderApi
import com.example.appcomandav20.features.orders.domain.repositories.UpdateColorOrderRespository
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class UpdateColorOrderImpl @Inject constructor(
    private val api: UpdateColorOrderApi
): UpdateColorOrderRespository {
    override suspend fun putUpdateColorOrder(comanda: String, idpedido: Int): NetworkResult<Void> {
        try {
            val response = api.putUpdateColorOrder(comanda,idpedido)
            return NetworkResult.Success(data = response)
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