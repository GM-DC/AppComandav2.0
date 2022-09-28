package com.example.appcomandav20.data.repositories

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.data.source.remote.ZoneApi
import com.example.appcomandav20.data.source.remote.dto.toUsuarioDC
import com.example.appcomandav20.data.source.remote.dto.toZoneModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.model.ZoneModel
import com.example.appcomandav20.domain.repositories.ZonesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ZoneRepositoryImpl @Inject constructor(
    private val api: ZoneApi
):ZonesRepository {
    override fun getZones(): Flow<NetworkResult<List<ZoneModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listZone: MutableList<ZoneModel> = mutableListOf()
            api.getZones().forEach {listZone.add(it.toZoneModel())}
            emit(NetworkResult.Success(listZone))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(
                message = "Huy! Algo salió mal",
                data = null
            ))
        } catch (e: IOException) {
            emit(NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                data = null
            ))
        }
    }
}