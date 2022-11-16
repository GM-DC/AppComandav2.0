package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.domain.database.entity.EntityLoginExito
import com.example.appcomandav20.domain.repositories.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoginUserResponseUseCase @Inject constructor(
    private val loginUserRepository : LoginUserRepository
) {
    operator fun invoke(): Flow<EntityLoginExito> {
        return loginUserRepository.getLoginUserResponseFromDatabase()
    }
}