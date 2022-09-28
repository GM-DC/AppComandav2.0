package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.CategoriesRepository
import com.example.appcomandav20.domain.repositories.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository : CategoriesRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<CategoryModel>>> {
        return categoryRepository.getCategories()
    }

}