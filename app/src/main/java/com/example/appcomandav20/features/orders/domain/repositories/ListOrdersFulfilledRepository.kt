package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.ListOrdersModel
import kotlinx.coroutines.flow.Flow

interface ListOrdersFulfilledRepository {

    fun getListOrdersFulfilled(idOrder: String): Flow<NetworkResult<List<ListOrdersModel>>>

}