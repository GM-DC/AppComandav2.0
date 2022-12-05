package com.example.appcomandav20.features.config.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomandav20.features.config.data.resouce.ConfigRepositoryImpl
import com.example.appcomandav20.features.config.domain.model.ConfigModel
import com.example.appcomandav20.features.config.domain.usecase.GetDataStoreUseCase
import com.example.appcomandav20.features.config.domain.usecase.SaveDataStoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val getDataStoreUseCase : GetDataStoreUseCase,
    private val saveDataStoreUseCase : SaveDataStoreUseCase,
): ViewModel() {

    private val _config : MutableLiveData<ConfigModel> = MutableLiveData()
    val config : MutableLiveData<ConfigModel> = _config

    private val _message : MutableLiveData<String> = MutableLiveData()
    var message: LiveData<String> = _message

    init {
        getDataStore()
    }

    fun saveDataStore(urlbase: String,port: String){
        viewModelScope.launch {
            saveDataStoreUseCase(urlbase,port)
        }
    }

    fun getDataStore(){
        viewModelScope.launch{
            getDataStoreUseCase().collect{
                _config.value = it
            }
        }
    }

}