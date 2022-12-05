package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.DishModel
import com.example.appcomandav20.features.orders.domain.repositories.DishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(
    private val dishesRepository : DishRepository
) {

    operator fun invoke(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>> {
        return dishesRepository.getDesh(nombrecategoria,moneda)
    }

}