package com.example.appcomandav20.features.users.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.users.domain.model.UsuarioDC
import com.example.appcomandav20.features.users.domain.repository.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUsuarioUseCase @Inject constructor(
    private val usuarioRepository : UsuarioRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<UsuarioDC>>> {
        return usuarioRepository.getUsuario()
    }

}