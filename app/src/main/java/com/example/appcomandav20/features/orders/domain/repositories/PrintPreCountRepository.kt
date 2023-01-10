package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.PreCount
import kotlinx.coroutines.flow.Flow

interface PrintPreCountRepository {

    fun getOrder(idPedido:String): Flow<NetworkResult<PreCount>>

}