package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.dto.LoginUserDTO
import com.example.appcomandav20.data.source.remote.response.LoginUserResponse
import com.example.appcomandav20.domain.model.LoginUserModel
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.LoginUserRepository
import com.example.appcomandav20.domain.repositories.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostLoginUserUseCase @Inject constructor(
    private val loginUserRepository : LoginUserRepository
) {

    suspend operator fun invoke(login: LoginUserModel): NetworkResult<LoginUserResponse> {
        return loginUserRepository.postLoginUser(login)
    }
}