package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.ListOrdersModel
import kotlinx.coroutines.flow.Flow

interface ListOrdersFulfilledRepository {

    fun getListOrdersFulfilled(idOrder: String): Flow<NetworkResult<List<ListOrdersModel>>>

}