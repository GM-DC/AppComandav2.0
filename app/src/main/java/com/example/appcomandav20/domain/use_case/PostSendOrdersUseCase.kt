package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.dto.SendOrdersDTO
import com.example.appcomandav20.data.source.remote.response.LoginUserResponse
import com.example.appcomandav20.data.source.remote.response.OrderResponseDTO
import com.example.appcomandav20.domain.model.OrderResponseModel
import com.example.appcomandav20.domain.repositories.SendOrdersRepository
import com.example.apppedido.domain.Model.SendOrdersModel
import javax.inject.Inject

class PostSendOrdersUseCase @Inject constructor(
    private val sendOrdersRepository : SendOrdersRepository
) {

    suspend operator fun invoke(orders: SendOrdersModel): NetworkResult<SendOrdersModel> {
        return sendOrdersRepository.postSendOrders(orders)
    }
}