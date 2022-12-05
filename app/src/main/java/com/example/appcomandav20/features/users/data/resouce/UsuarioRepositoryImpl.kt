package com.example.appcomandav20.features.users.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.users.domain.model.UsuarioDC
import com.example.appcomandav20.features.users.data.remote.UsuarioApi
import com.example.appcomandav20.features.users.data.remote.dto.toUsuarioDC
import com.example.appcomandav20.features.users.domain.repository.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val api: UsuarioApi
): UsuarioRepository {
    override fun getUsuario(): Flow<NetworkResult<List<UsuarioDC>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listusuario: MutableList<UsuarioDC> = mutableListOf()
            api.getUsuarios().forEach { listusuario.add(it.toUsuarioDC()) }
            emit(NetworkResult.Success( listusuario))
        } catch (e: HttpException) {
            emit(
                NetworkResult.Error(
                message = "Huy! Algo salió mal",
                data = null
            ))
        } catch (e: IOException) {
            emit(
                NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                data = null
            ))
        }
    }

}