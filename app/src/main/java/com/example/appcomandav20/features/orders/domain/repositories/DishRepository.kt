package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.DishModel
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    fun getDesh(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>>
}