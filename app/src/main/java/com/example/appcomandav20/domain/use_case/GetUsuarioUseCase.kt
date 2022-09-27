package com.example.rickandmorty.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.ListUsuarioModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUsuarioUseCase @Inject constructor(
    private val usuarioRepository : UsuarioRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<UsuarioDC>>> {
        return usuarioRepository.getUsuario()
    }

}