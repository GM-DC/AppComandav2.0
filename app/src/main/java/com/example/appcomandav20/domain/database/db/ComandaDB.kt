package com.example.appcomandav20.domain.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appcomandav20.domain.database.dao.DaoLoginExito
import com.example.appcomandav20.domain.database.entity.EntityLoginExito
import com.example.apppedido.DataBase.*

@Database(
    entities = [EntityZona::class,
                EntityCategoria::class,
                EntityUsuario::class,
                EntityLoginExito::class],
                version = 1)

    abstract class ComandaDB : RoomDatabase() {
    abstract fun daoZona(): DaoZona
    abstract fun daoCategoria(): DaoCategoria
    abstract fun daoUsuario(): DaoUsuario
    abstract fun daoLoginExito(): DaoLoginExito
}