package com.example.appcomandav20.features.orders.domain.repositories

import com.example.appcomandav20.features.orders.domain.model.ListOrdersModel

interface SaveOrdersRepository {

    suspend fun getSaveOrdersByZoneTable(id: Int): ListOrdersModel?

    suspend fun insertSaveOrders(zone:String,table:String, lista: ListOrdersModel)

}