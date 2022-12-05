package com.example.appcomandav20.features.zones.domain.repository

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.domain.model.TableModel
import kotlinx.coroutines.flow.Flow

interface TablesRepository {

    fun getTables(filter:String): Flow<NetworkResult<List<TableModel>>>

}