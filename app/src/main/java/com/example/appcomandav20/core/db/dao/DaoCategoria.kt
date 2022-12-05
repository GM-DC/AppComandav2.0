package com.example.appcomandav20.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcomandav20.core.db.entity.EntityCategoria

@Dao
interface DaoCategoria {

    @Query("SELECT EXISTS(SELECT * FROM EntityCategoria)")
    fun isExistsCategoria(): Boolean

    @Query("SELECT * FROM EntityCategoria")
    fun getAllCategoria(): List<EntityCategoria>

    @Insert
    fun insertCategoria( insertzonas: EntityCategoria)

    @Query("DELETE FROM EntityCategoria")
    fun deleteTableCategoria()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'EntityCategoria'")
    fun clearPrimaryKeyCategoria()
}