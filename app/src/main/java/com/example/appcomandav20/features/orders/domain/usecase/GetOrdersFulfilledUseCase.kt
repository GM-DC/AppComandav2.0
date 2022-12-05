package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.ListOrdersModel
import com.example.appcomandav20.features.orders.domain.repositories.ListOrdersFulfilledRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersFulfilledUseCase @Inject constructor(
    private val listOrdersFulfilledRepository : ListOrdersFulfilledRepository
) {

    operator fun invoke(idPedido:String): Flow<NetworkResult<List<ListOrdersModel>>> {
        return listOrdersFulfilledRepository.getListOrdersFulfilled(idPedido)
    }

}