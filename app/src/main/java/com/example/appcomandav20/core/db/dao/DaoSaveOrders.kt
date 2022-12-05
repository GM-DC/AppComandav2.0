package com.example.appcomandav20.core.db.dao

import androidx.room.*
import com.example.appcomandav20.core.db.entity.EntitySaveOrders
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoSaveOrders {

    @Query("SELECT EXISTS(SELECT * FROM EntitySaveOrders)")
    fun isExistsSaveOrders(): Boolean

    @Query("SELECT * FROM EntitySaveOrders")
    fun getAllSaveOrders(): Flow<List<EntitySaveOrders>>

    @Query("SELECT * FROM EntitySaveOrders WHERE (zona = :zone) AND (mesa LIKE :table) " )
    suspend fun getUserByZoneTable(zone: Int,table:Int): EntitySaveOrders?

    @Insert
    fun insertSaveOrders( insertzonas: EntitySaveOrders)

    @Query("DELETE FROM EntitySaveOrders")
    fun deleteSaveOrders()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'EntitySaveOrders'")
    fun clearPrimaryKey()

}