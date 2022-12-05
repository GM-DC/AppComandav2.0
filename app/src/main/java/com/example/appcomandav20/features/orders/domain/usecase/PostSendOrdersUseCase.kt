package com.example.appcomandav20.features.orders.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.repositories.SendOrdersRepository
import com.example.appcomandav20.features.orders.domain.model.SendOrdersModel
import javax.inject.Inject

class PostSendOrdersUseCase @Inject constructor(
    private val sendOrdersRepository : SendOrdersRepository
) {

    suspend operator fun invoke(orders: SendOrdersModel): NetworkResult<SendOrdersModel> {
        return sendOrdersRepository.postSendOrders(orders)
    }
}