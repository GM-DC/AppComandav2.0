package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun getCategories(): Flow<NetworkResult<List<CategoryModel>>>
}