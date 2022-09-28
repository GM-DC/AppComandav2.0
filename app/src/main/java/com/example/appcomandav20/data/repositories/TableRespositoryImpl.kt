package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.TableApi
import com.example.appcomandav20.data.source.remote.UsuarioApi
import com.example.appcomandav20.data.source.remote.dto.TableDTO
import com.example.appcomandav20.data.source.remote.dto.toTableModel
import com.example.appcomandav20.data.source.remote.dto.toUsuarioDC
import com.example.appcomandav20.domain.model.TableModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.TablesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TableRespositoryImpl @Inject constructor(
    private val api: TableApi
):TablesRepository {
    override fun getTables(filter: String): Flow<NetworkResult<List<TableModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listTable: MutableList<TableModel> = mutableListOf()
            api.getTable(filter).forEach { listTable.add(it.toTableModel())  }
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