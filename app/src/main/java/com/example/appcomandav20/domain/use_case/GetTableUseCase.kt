package com.example.appcomandav20.domain.use_case

import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.TableModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.repositories.TablesRepository
import com.example.appcomandav20.domain.repositories.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import java.util.logging.Filter
import javax.inject.Inject

class GetTableUseCase @Inject constructor(
    private val tablesRepository : TablesRepository
) {

    operator fun invoke(filter: String): Flow<NetworkResult<List<TableModel>>> {
        return tablesRepository.getTables(filter)
    }

}