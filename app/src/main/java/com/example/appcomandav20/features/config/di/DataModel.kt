package com.example.appcomandav20.features.config.di

import android.content.Context
import com.example.appcomandav20.features.config.data.resouce.ConfigRepositoryImpl
import com.example.appcomandav20.features.users.data.resouce.UsuarioRepositoryImpl
import com.example.appcomandav20.features.users.domain.repository.UsuarioRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModel {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)= ConfigRepositoryImpl(context)

}