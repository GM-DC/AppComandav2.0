package com.example.appcomandav20.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appcomandav20.core.db.entity.EntityZona

@Dao
interface DaoZona {

    @Query("SELECT EXISTS(SELECT * FROM EntityZona)")
    fun isExistsZonas(): Boolean

    @Query("SELECT * FROM EntityZona")
    fun getAllZonas(): List<EntityZona>

    @Query("SELECT idZona FROM EntityZona WHERE id = :id")
    fun getZonaId(id: Int): String

    @Query("SELECT nombreZonas FROM EntityZona WHERE id = :id")
    fun getZonaNombre(id: Int): String

    @Insert
    fun insertZona( insertzonas: EntityZona)

    @Query("DELETE FROM EntityZona")
    fun deleteTable()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'EntityZona'")
    fun clearPrimaryKey()

}