package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.core.NetworkResult

interface PrintPreCountRepository {

    suspend fun putUpdateStateTable(idZona:String,idMesa:Int,estadoMesa:String,nameMozo:String): NetworkResult<Void>

}