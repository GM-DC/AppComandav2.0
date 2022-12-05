package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.CategoryModel
import com.example.appcomandav20.features.orders.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository : CategoriesRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<CategoryModel>>> {
        return categoryRepository.getCategories()
    }

}