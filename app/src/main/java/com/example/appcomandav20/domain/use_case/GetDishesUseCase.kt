package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.CategoryModel
import com.example.appcomandav20.domain.model.DishModel
import com.example.appcomandav20.domain.repositories.CategoriesRepository
import com.example.appcomandav20.domain.repositories.DishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(
    private val dishesRepository : DishRepository
) {

    operator fun invoke(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>> {
        return dishesRepository.getDesh(nombrecategoria,moneda)
    }

}