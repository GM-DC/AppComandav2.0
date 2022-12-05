package com.example.appcomandav20.features.zones.data.resouce

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.data.remote.ZoneApi
import com.example.appcomandav20.features.zones.data.remote.dto.toZoneModel
import com.example.appcomandav20.features.zones.domain.model.ZoneModel
import com.example.appcomandav20.features.zones.domain.repository.ZonesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ZoneRepositoryImpl @Inject constructor(
    private val api: ZoneApi
): ZonesRepository {
    override fun getZones(): Flow<NetworkResult<List<ZoneModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listZone: MutableList<ZoneModel> = mutableListOf()
            api.getZones().forEach {listZone.add(it.toZoneModel())}
            emit(NetworkResult.Success(listZone))
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