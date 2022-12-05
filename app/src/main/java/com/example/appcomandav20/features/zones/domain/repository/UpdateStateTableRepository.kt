package com.example.appcomandav20.features.zones.domain.repository

import com.example.appcomandav20.core.NetworkResult

interface UpdateStateTableRepository {

    suspend fun putUpdateStateTable(idZona:String,idMesa:Int,estadoMesa:String,nameMozo:String): NetworkResult<Void>

}