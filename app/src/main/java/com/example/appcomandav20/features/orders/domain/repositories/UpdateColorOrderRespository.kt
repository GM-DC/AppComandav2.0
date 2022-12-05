package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult

interface UpdateColorOrderRespository {

    suspend fun putUpdateColorOrder(comanda:String, idpedido:Int): NetworkResult<Void>

}