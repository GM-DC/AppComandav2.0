package com.example.appcomandav20.features.config.domain.usecase

import com.example.appcomandav20.features.config.domain.model.ConfigModel
import com.example.appcomandav20.features.config.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataStoreUseCase @Inject constructor(
    private val configRepository : ConfigRepository
) {
    suspend operator fun invoke():Flow<ConfigModel> {
        return configRepository.getPhoneBook()
    }
}