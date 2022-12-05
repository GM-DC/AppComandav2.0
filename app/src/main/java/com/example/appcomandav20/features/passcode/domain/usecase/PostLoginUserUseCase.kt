package com.example.appcomandav20.features.passcode.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.passcode.domain.model.LoginUserModel
import com.example.appcomandav20.features.passcode.domain.model.LoginUserResponseModel
import com.example.appcomandav20.features.passcode.domain.repository.LoginUserRepository
import javax.inject.Inject

class PostLoginUserUseCase @Inject constructor(
    private val loginUserRepository : LoginUserRepository
) {
    suspend operator fun invoke(login: LoginUserModel): NetworkResult<LoginUserResponseModel> {
        return loginUserRepository.postLoginUser(login)
    }
}