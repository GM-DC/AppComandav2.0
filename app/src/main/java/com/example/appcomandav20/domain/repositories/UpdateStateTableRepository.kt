package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.apppedido.domain.Model.SendOrdersModel

interface UpdateStateTableRepository {

    suspend fun putUpdateStateTable(idZona:String,idMesa:Int,estadoMesa:String,nameMozo:String): NetworkResult<Void>

}