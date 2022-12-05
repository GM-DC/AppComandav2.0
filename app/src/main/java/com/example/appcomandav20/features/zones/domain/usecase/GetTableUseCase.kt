package com.example.appcomandav20.features.zones.domain.usecase

import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.zones.domain.model.TableModel
import com.example.appcomandav20.features.zones.domain.repository.TablesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTableUseCase @Inject constructor(
    private val tablesRepository : TablesRepository
) {

    operator fun invoke(filter: String): Flow<NetworkResult<List<TableModel>>> {
        return tablesRepository.getTables(filter)
    }

}