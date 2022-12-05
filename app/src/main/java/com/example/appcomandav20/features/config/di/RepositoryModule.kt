package com.example.appcomandav20.features.config.di

import com.example.appcomandav20.features.config.data.resouce.ConfigRepositoryImpl
import com.example.appcomandav20.features.config.domain.repository.ConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindConfigRepository(impl: ConfigRepositoryImpl): ConfigRepository
}