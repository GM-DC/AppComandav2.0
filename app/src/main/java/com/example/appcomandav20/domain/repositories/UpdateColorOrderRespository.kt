package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult

interface UpdateColorOrderRespository {

    suspend fun putUpdateColorOrder(comanda:String, idpedido:Int): NetworkResult<Void>

}