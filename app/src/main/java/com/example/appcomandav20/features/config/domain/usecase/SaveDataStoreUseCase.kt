package com.example.appcomandav20.features.config.domain.usecase

import com.example.appcomandav20.features.config.domain.model.ConfigModel
import com.example.appcomandav20.features.config.domain.repository.ConfigRepository
import javax.inject.Inject

class SaveDataStoreUseCase @Inject constructor(
    private val configRepository : ConfigRepository
) {
    suspend operator fun invoke(urlbase:String, port:String){
        configRepository.savePhoneBook(ConfigModel(urlbase,port))
    }
}