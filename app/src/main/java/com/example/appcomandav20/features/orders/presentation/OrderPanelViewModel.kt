package com.example.appcomandav20.features.orders.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcomandav20.core.NetworkResult
import com.example.appcomandav20.features.orders.domain.model.*
import com.example.appcomandav20.features.orders.domain.usecase.*
import com.example.appcomandav20.features.orders.presentation.OrderPanelFrag.Companion.listOrders
import com.example.appcomandav20.features.zones.domain.model.TableModel
import com.example.appcomandav20.features.zones.domain.usecase.GetTableUseCase
import com.example.appcomandav20.util.PrintOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderPanelViewModel @Inject constructor(
    private val getCategoryUseCase : GetCategoryUseCase,
    private val getDishesUseCase : GetDishesUseCase,
    private val postSendOrdersUseCase : PostSendOrdersUseCase,
    private val getOrdersFulfilledUseCase : GetOrdersFulfilledUseCase,
    private val putUpdateStateTableUseCase : PutUpdateStateTableUseCase,
    private val getOrderUseCase : GetOrderUseCase,
    private val putUpdateColorOrderUseCase : PutUpdateColorOrderUseCase,
    private val getTableUseCase : GetTableUseCase,
    private val getPreCountUseCase : GetPreCountUseCase,
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private val _message: MutableLiveData<String> = MutableLiveData()
    var message: LiveData<String> = _message

    private val _listCategories: MutableLiveData<MutableList<CategoryModel>> = MutableLiveData()
    var listCategories: LiveData<MutableList<CategoryModel>> = _listCategories

    private val _listDish: MutableLiveData<MutableList<DishModel>> = MutableLiveData()
    var listDish: LiveData<MutableList<DishModel>> = _listDish

    private val _listOrders: MutableLiveData<MutableList<ListOrdersModel>> = MutableLiveData()
    var listOrdersVM: LiveData<MutableList<ListOrdersModel>> = _listOrders

    private val _listTable: MutableLiveData<MutableList<TableModel>> = MutableLiveData()
    var listTable: LiveData<MutableList<TableModel>> = _listTable

    private val _responseOrder: MutableLiveData<SendOrdersModel> = MutableLiveData()
    var responseOrder: LiveData<SendOrdersModel> = _responseOrder

    private val _UpdateStateTable: MutableLiveData<Void> = MutableLiveData()
    var UpdateStateTable: LiveData<Void> = _UpdateStateTable

    private val _listComanda: MutableLiveData<MutableList<OrderModel>> = MutableLiveData()
    var ListComanda: LiveData<MutableList<OrderModel>> = _listComanda

    private val _resulPreCount: MutableLiveData<PreCount> = MutableLiveData()
    var resulPreCount: LiveData<PreCount> = _resulPreCount


    lateinit var job: Job

    init {
        getCategoryData()
        //getLoginUserResponse()
    }


    fun getTableByZoneTable(idZone: String,table: String) {
        job = viewModelScope.launch {
            getTableUseCase("piso eq '$idZone' and idMesa eq $table").onEach { result ->
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


    fun cancelarCorrutine(){
        job.cancel()
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
            getDishesUseCase(nombrecategoria = nameZona, moneda = "0001").onEach { result ->
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
    fun postSendOrder(orderSend: SendOrdersModel) {
        viewModelScope.launch {
            postSendOrdersUseCase(orderSend).also { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _responseOrder.value = result.data!!
                        getOrder(result.data.iD_PEDIDO.toString(),result.data)
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
    fun getOrderFulfilled(ipPedido: String) {
        viewModelScope.launch {
            getOrdersFulfilledUseCase(ipPedido).onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _listOrders.value = result.data!!.toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _listOrders.value = emptyList<ListOrdersModel>().toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _listOrders.value = emptyList<ListOrdersModel>().toMutableList()
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }
    fun getPrintPreCount(ipPedido: String) {
        viewModelScope.launch {
            getPreCountUseCase(ipPedido).onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _resulPreCount.value = result.data!!
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }
    fun putUpdateStateTable(idZona: String,idMesa: Int,estadoMesa: String,nameMozo: String) {
        viewModelScope.launch {
            putUpdateStateTableUseCase(idZona,idMesa,estadoMesa,nameMozo).also { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _UpdateStateTable.value = result.data!!
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }
        }
    }
    fun getOrder(ipPedido: String, resultSendOrder :SendOrdersModel) {
        viewModelScope.launch {
            getOrderUseCase(ipPedido).onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        printOrderComanda(result.data!!,resultSendOrder)
                        _listComanda.value = result.data.toMutableList()
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }.launchIn(this)
        }
    }

    fun putUpdateColorOrder(comanda: String,idpedido: Int) {
        viewModelScope.launch {
            putUpdateColorOrderUseCase(comanda,idpedido).also { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _isLoading.value = false
                    }
                    is NetworkResult.Error -> {
                        _isLoading.value = false
                    }
                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                    }
                }
            }
        }
    }

    fun printOrderComanda(resp :List<OrderModel>, resultSendOrder: SendOrdersModel){
        CoroutineScope(Dispatchers.IO).launch {
            val listaCodComanda: ArrayList<String> = ArrayList()
            val idPedido = resultSendOrder.iD_PEDIDO.toString()

            resultSendOrder.detalle.forEach {
                listaCodComanda.add(it.comanda)
            }

            val listaDetalleComanda = ArrayList<OrderDetailModel>()
            val listaComanda = ArrayList<OrderModel>()
            val impComanda = PrintOrder()

            resp.forEach { cabezera ->
                cabezera.detalle.forEach { detalleComanda ->
                    for (i in listOrders.indices){
                        if(detalleComanda.producto == listOrders[i].namePlato && listOrders[i].estadoPedido == "PENDIENTE"){
                            listaDetalleComanda.add(
                                OrderDetailModel(
                                    detalleComanda.iD_PRODUCTO,
                                    detalleComanda.producto,
                                    listOrders[i].cantidad,
                                    detalleComanda.precio,
                                    detalleComanda.precio*listOrders[i].cantidad,
                                    listOrders[i].observacion,
                                    detalleComanda.noM_IMP,
                                    secuencia = i)
                            )
                        }
                    }
                }
                listaComanda.add(OrderModel(cabezera.numerO_PEDIDO,cabezera.destino,cabezera.zona,cabezera.mesa,cabezera.mesero,cabezera.rutacomanda,cabezera.fechayhora,listaDetalleComanda))
            }

            var init = " "
            listaCodComanda.forEach {
                if (init != it){
                    putUpdateColorOrder(it,idPedido.toInt())
                    init = it
                }
            }

            listaComanda.forEach {
                impComanda.printTcp(it.rutacomanda,9100,it)
            }
        }
    }
}