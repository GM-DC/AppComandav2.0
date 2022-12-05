package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.data.remote.OrdersFulfilledApi
import com.example.appcomandav20.features.orders.data.remote.dto.toListOrdersModel
import com.example.appcomandav20.features.orders.domain.model.ListOrdersModel
import com.example.appcomandav20.features.orders.domain.repositories.ListOrdersFulfilledRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListOrdersFulfilledRepositoryImpl  @Inject constructor(

    private val api: OrdersFulfilledApi
): ListOrdersFulfilledRepository {
    override fun getListOrdersFulfilled(idOrder:String): Flow<NetworkResult<List<ListOrdersModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listOrderFufilled: MutableList<ListOrdersModel> = mutableListOf()
            api.getOrdersFulfilled(idOrder).forEach { listOrderFufilled.add(it.toListOrdersModel()) }
            emit(NetworkResult.Success( listOrderFufilled))
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