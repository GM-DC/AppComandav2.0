package com.example.appcomandav20.features.zones.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.data.remote.TableApi
import com.example.appcomandav20.features.zones.data.remote.dto.toTableModel
import com.example.appcomandav20.features.zones.domain.model.TableModel
import com.example.appcomandav20.features.zones.domain.repository.TablesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TableRespositoryImpl @Inject constructor(
    private val api: TableApi
): TablesRepository {
    override fun getTables(filter: String): Flow<NetworkResult<List<TableModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listTable: MutableList<TableModel> = mutableListOf()
            var dato = filter
            do{
                listTable.clear()
                api.getTable(filter).forEach { listTable.add(it.toTableModel())  }
                emit(NetworkResult.Success( listTable ))
                delay(60000)
                if(dato.isEmpty()){
                    dato = filter
                }
            }while(dato == filter)

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