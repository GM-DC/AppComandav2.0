package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.DishModel
import com.example.appcomandav20.domain.model.ListOrdersModel
import com.example.appcomandav20.domain.repositories.DishRepository
import com.example.appcomandav20.domain.repositories.ListOrdersFulfilledRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersFulfilledUseCase @Inject constructor(
    private val listOrdersFulfilledRepository : ListOrdersFulfilledRepository
) {

    operator fun invoke(idPedido:String): Flow<NetworkResult<List<ListOrdersModel>>> {
        return listOrdersFulfilledRepository.getListOrdersFulfilled(idPedido)
    }

}