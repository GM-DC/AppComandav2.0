package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.data.remote.DishApi
import com.example.appcomandav20.features.orders.data.remote.dto.toDishModel
import com.example.appcomandav20.features.orders.domain.model.DishModel
import com.example.appcomandav20.features.orders.domain.repositories.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val api: DishApi
): DishRepository {
    override fun getDesh(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listTable: MutableList<DishModel> = mutableListOf()
            api.getDishes(nombrecategoria,"0001").forEach { listTable.add(it.toDishModel())  }
            emit(NetworkResult.Success( listTable ))
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