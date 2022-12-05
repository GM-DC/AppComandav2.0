package com.example.appcomandav20.features.orders.data.resouce

import com.example.appcomandav20.features.orders.domain.model.ListOrdersModel
import com.example.appcomandav20.features.orders.domain.repositories.SaveOrdersRepository


class SaveOrdersRepositoryImpl:SaveOrdersRepository {
    override suspend fun getSaveOrdersByZoneTable(id: Int): ListOrdersModel? {
        TODO("Not yet implemented")
    }

    override suspend fun insertSaveOrders(zone: String, table: String, lista: ListOrdersModel) {
        TODO("Not yet implemented")
    }
}