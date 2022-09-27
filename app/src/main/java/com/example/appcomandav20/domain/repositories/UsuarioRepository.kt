package com.example.appcomandav20.domain.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.ListUsuarioModel
import com.example.appcomandav20.domain.model.UsuarioDC
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {

     fun getUsuario(): Flow<NetworkResult<List<UsuarioDC>>>
}