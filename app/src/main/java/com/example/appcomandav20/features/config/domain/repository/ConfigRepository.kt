package com.example.appcomandav20.features.config.domain.repository

import com.example.appcomandav20.features.config.domain.model.ConfigModel
import kotlinx.coroutines.flow.Flow


interface ConfigRepository {

    suspend fun savePhoneBook(configModel: ConfigModel)

    suspend fun getPhoneBook(): Flow<ConfigModel>

}