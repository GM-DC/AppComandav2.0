package com.example.appcomandav20.view.ui.Usuario

import androidx.lifecycle.*
import com.example.appcomandav20.data.NetworkResult
import com.example.appcomandav20.domain.model.LoginUserModel
import com.example.appcomandav20.domain.model.LoginUserResponseModel
import com.example.appcomandav20.domain.model.UsuarioDC
import com.example.appcomandav20.domain.use_case.PostLoginUserUseCase
import com.example.rickandmorty.domain.use_case.GetUsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclerUsuarioViewModel @Inject constructor(
    private val getUsuarioUseCase : GetUsuarioUseCase,
    private val postLoginUserUseCase : PostLoginUserUseCase
) : ViewModel()  {

    private var _loginResult: MutableLiveData<LoginUserResponseModel> = MutableLiveData()
    var loginResult: LiveData<LoginUserResponseModel> = _loginResult

    private val _listState : MutableLiveData<MutableList<UsuarioDC>> = MutableLiveData()
    var listState: LiveData<MutableList<UsuarioDC>> = _listState

    private val _message : MutableLiveData<String> = MutableLiveData()
    var message: LiveData<String> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    init {
        getUsuarioData()
    }

    fun getUsuarioData() {
        viewModelScope.launch {
            getUsuarioUseCase().onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _listState.value = result.data!!.toMutableList()
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

    fun loginUser(usuario: String, passwords: String) {
        viewModelScope.launch {
            val loginRequest = LoginUserModel(usuariomozo = usuario,passmozo = passwords)
            postLoginUserUseCase(loginRequest).also { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _loginResult.value = result.data!!
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
            }
        }
    }


}