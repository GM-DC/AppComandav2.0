package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.dto.TableDTO
import com.example.appcomandav20.domain.model.TableModel
import com.example.appcomandav20.domain.model.UsuarioDC
import kotlinx.coroutines.flow.Flow

interface TablesRepository {

    fun getTables(filter:String): Flow<NetworkResult<List<TableModel>>>

}