package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.DishModel
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    fun getDesh(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>>
}