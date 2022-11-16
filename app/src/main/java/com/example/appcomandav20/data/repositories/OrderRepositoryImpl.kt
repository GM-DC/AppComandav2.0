package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.DishApi
import com.example.appcomandav20.data.source.remote.OrderApi
import com.example.appcomandav20.data.source.remote.dto.toDishModel
import com.example.appcomandav20.data.source.remote.dto.toOrderModel
import com.example.appcomandav20.domain.model.DishModel
import com.example.appcomandav20.domain.model.OrderModel
import com.example.appcomandav20.domain.repositories.DishRepository
import com.example.appcomandav20.domain.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
): OrderRepository {
    override fun getOrder(idPedido: String): Flow<NetworkResult<List<OrderModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val dato = api.getComanda(idPedido).map { it.toOrderModel() }
            emit(NetworkResult.Success( dato ))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(
                message = "Huy! Algo salió mal",
                data = null
            ))
        } catch (e: IOException) {
            emit(NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                data = null
            ))
        }
    }
}