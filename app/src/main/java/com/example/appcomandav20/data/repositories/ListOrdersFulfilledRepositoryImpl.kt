package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.OrdersFulfilledApi
import com.example.appcomandav20.data.source.remote.UsuarioApi
import com.example.appcomandav20.data.source.remote.dto.toListOrdersModel
import com.example.appcomandav20.data.source.remote.dto.toUsuarioDC
import com.example.appcomandav20.domain.model.ListOrdersModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.ListOrdersFulfilledRepository
import com.example.appcomandav20.domain.repositories.UsuarioRepository
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