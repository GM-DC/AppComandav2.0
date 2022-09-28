package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.ZoneModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun getCategories(): Flow<NetworkResult<List<CategoryModel>>>
}