package com.example.appcomandav20.view.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.*
import com.example.appcomandav20.domain.use_case.GetCategoryUseCase
import com.example.appcomandav20.domain.use_case.GetDishesUseCase
import com.example.appcomandav20.domain.use_case.GetTableUseCase
import com.example.appcomandav20.domain.use_case.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPanelViewModel @Inject constructor(
    private val getZonesUseCase : GetZonesUseCase,
    private val getTableUseCase : GetTableUseCase,
    private val getCategoryUseCase : GetCategoryUseCase,
    private val getDishesUseCase : GetDishesUseCase,
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private val _message : MutableLiveData<String> = MutableLiveData()
    var message: LiveData<String> = _message

    private var _listZones: MutableLiveData<MutableList<ZoneModel>> = MutableLiveData()
    var listZones: LiveData<MutableList<ZoneModel>> = _listZones

    private val _listTable : MutableLiveData<MutableList<TableModel>> = MutableLiveData()
    var listTable: LiveData<MutableList<TableModel>> = _listTable

    private val _listCategories : MutableLiveData<MutableList<CategoryModel>> = MutableLiveData()
    var listCategories: LiveData<MutableList<CategoryModel>> = _listCategories

    private val _listDish : MutableLiveData<MutableList<DishModel>> = MutableLiveData()
    var listDish: LiveData<MutableList<DishModel>> = _listDish

    init {
        getUsuarioData()
        getCategoryData()
    }

    private fun getUsuarioData() {
        viewModelScope.launch {
            getZonesUseCase().onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _listZones.value = result.data!!.toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _message.value = result.message ?: "Error Desconocido"
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }

    fun getTableData(idZone: String) {
        viewModelScope.launch {
            getTableUseCase("piso eq '$idZone' and tipo eq 'A'").onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _listTable.value = result.data!!.toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _message.value = result.message ?: "Error Desconocido"
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getCategoryData() {
        viewModelScope.launch {
            getCategoryUseCase().onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _listCategories.value = result.data!!.toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _message.value = result.message ?: "Error Desconocido"
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }

    fun getDishData(nameZona: String) {
        viewModelScope.launch {
            getDishesUseCase(nombrecategoria = nameZona,moneda ="0001").onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _listDish.value = result.data!!.toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _message.value = result.message ?: "Error Desconocido"
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }

}