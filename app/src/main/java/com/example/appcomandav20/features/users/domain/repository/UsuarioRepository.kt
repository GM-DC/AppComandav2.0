package com.example.appcomandav20.features.users.domain.repository

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.users.domain.model.UsuarioDC
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {

     fun getUsuario(): Flow<NetworkResult<List<UsuarioDC>>>
}