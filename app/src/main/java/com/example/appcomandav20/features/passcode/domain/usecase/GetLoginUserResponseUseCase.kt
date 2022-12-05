package com.example.appcomandav20.features.passcode.domain.usecase

import com.example.appcomandav20.core.db.entity.EntityLoginExito
import com.example.appcomandav20.features.passcode.domain.repository.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginUserResponseUseCase @Inject constructor(
    private val loginUserRepository : LoginUserRepository
) {
    operator fun invoke(): Flow<EntityLoginExito> {
        return loginUserRepository.getLoginUserResponseFromDatabase()
    }
}