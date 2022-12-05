package com.example.appcomandav20.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcomandav20.core.db.dao.*
import com.example.appcomandav20.core.db.entity.*

@Database(
    entities = [EntityZona::class,
                EntityCategoria::class,
                EntityUsuario::class,
                EntityLoginExito::class,
                EntitySaveOrders::class],
                version = 1)
    abstract class ComandaDB : RoomDatabase() {
    abstract fun daoZona(): DaoZona
    abstract fun daoCategoria(): DaoCategoria
    abstract fun daoUsuario(): DaoUsuario
    abstract fun daoLoginExito(): DaoLoginExito
    abstract fun daoSaveOrders(): DaoSaveOrders
}