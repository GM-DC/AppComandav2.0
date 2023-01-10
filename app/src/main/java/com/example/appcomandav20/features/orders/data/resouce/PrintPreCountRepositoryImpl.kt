package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.data.remote.PreCountApi
import com.example.appcomandav20.features.orders.domain.model.OrderModel
import com.example.appcomandav20.features.orders.domain.model.PreCount
import com.example.appcomandav20.features.orders.domain.repositories.OrderRepository
import com.example.appcomandav20.features.orders.domain.repositories.PrintPreCountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PrintPreCountRepositoryImpl @Inject constructor(
    private val api: PreCountApi
): PrintPreCountRepository {
    override fun getOrder(idPedido: String): Flow<NetworkResult<PreCount>> = flow{
        emit(NetworkResult.Loading())
        try {
            val dato = api.getPreCuenta(idPedido).toPreCount()
            emit(NetworkResult.Success( dato ))
        } catch (e: HttpException) {
            emit(
                NetworkResult.Error(
                    message = "Huy! Algo salió mal",
                    data = null
                ))
        } catch (e: IOException) {
            emit(
                NetworkResult.Error(
                    message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                    data = null
                ))
        }

    }

}