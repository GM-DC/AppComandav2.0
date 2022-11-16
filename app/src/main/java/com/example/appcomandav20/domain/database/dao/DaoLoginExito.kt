package com.example.appcomandav20.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.database.entity.EntityLoginExito
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoLoginExito {
    @Query("SELECT EXISTS(SELECT * FROM EntityLoginExito)")
    suspend fun isExists(): Boolean

    @Query("SELECT * FROM EntityLoginExito")
    fun getAll(): Flow<EntityLoginExito>

    @Insert
    suspend fun insert( insertzonas: EntityLoginExito)

    @Query("DELETE FROM EntityLoginExito")
    suspend fun deleteTable()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'EntityLoginExito'")
    suspend fun clearPrimaryKey()
}