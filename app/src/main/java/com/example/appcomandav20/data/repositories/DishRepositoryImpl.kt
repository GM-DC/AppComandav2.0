package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.DishApi
import com.example.appcomandav20.data.source.remote.TableApi
import com.example.appcomandav20.data.source.remote.dto.toDishModel
import com.example.appcomandav20.data.source.remote.dto.toTableModel
import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.DishModel
import com.example.appcomandav20.domain.model.TableModel
import com.example.appcomandav20.domain.repositories.DishRepository
import com.example.appcomandav20.domain.repositories.TablesRepository
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
            val dato = api.getDishes(nombrecategoria,"0001")
            println(dato)
            api.getDishes(nombrecategoria,"0001").forEach { listTable.add(it.toDishModel())  }
            emit(NetworkResult.Success( listTable ))
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