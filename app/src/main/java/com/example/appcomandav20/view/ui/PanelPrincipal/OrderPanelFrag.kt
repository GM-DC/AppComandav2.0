package com.example.appcomandav20.view.ui.PanelPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.databinding.FragMainPanelBinding
import com.example.appcomandav20.databinding.FragOrderPanelBinding
import com.example.appcomandav20.domain.model.*
import com.example.appcomandav20.util.PrintOrder
import com.example.appcomandav20.util.utils
import com.example.appcomandav20.view.ui.PanelPrincipal.adapter.CategoryAdapter
import com.example.appcomandav20.view.ui.PanelPrincipal.adapter.DishAdapter
import com.example.appcomandav20.view.ui.PanelPrincipal.adapter.OrderAdapter
import com.example.apppedido.domain.Model.DetalleModel
import com.example.apppedido.domain.Model.SendOrdersModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class OrderPanelFrag : Fragment() {
    private var _binding: FragOrderPanelBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainPanelViewModel

    private lateinit var categoryAdapter: CategoryAdapter
    private val listaCategories = mutableListOf<CategoryModel>()

    private lateinit var dishAdapter: DishAdapter
    private val listDish = mutableListOf<DishModel>()

    private lateinit var orderAdapter: OrderAdapter
    private val listOrders = mutableListOf<ListOrdersModel>()
    private val listSendOrders = mutableListOf<ListOrdersModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            activity?.let {
                ViewModelProvider(it)[MainPanelViewModel::class.java]
            }!!
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View {
        _binding = FragOrderPanelBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategoryRecyclerview()
        initDishRecyclerview()
        initListOrdersRecyclerview()
        initData()
        messageState()

        getListCategory()
        getListDish()
        getListOrders()
        enableSwipeToDelete()

        eventsHandlers()
        backPreseent()
    }

    private fun backPreseent() {
        val onBackPressedCallback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,onBackPressedCallback)
    }

    private fun initData() {
        val idTable = OrderPanelFragArgs.fromBundle(requireArguments()).mesa
        val nameZone = OrderPanelFragArgs.fromBundle(requireArguments()).zona

        binding.tvTable.text = "Mesa $idTable"
        binding.tvZone.text = "$nameZone"
    }

    //----- EVENTS CLICK
    private fun eventsHandlers() {
        binding.btEnviarComanda.setOnClickListener{ sendOrders() }
        binding.btnLimpiar.setOnClickListener { clearListOrders() }
        binding.btnPrecuenta.setOnClickListener { printPreCount() }
    }

    private fun printPreCount() {

    }

    private fun clearListOrders() {
        var size = listOrders.size
        var cont = 0

        if (listOrders.size>0){
            do{
                if (listOrders[cont].estadoPedido=="PENDIENTE"){
                    listOrders.removeAt(cont)
                    orderAdapter.notifyItemRemoved(cont)
                    cont=0
                    size-=1
                }else{
                    cont+=1
                }
            }while(size != cont)
        }

        //orderAdapter.notifyDataSetChanged()
        //actualizarPrecioTotal()
    }

    //----- SEND ORDERS
    private fun sendOrders() {
        viewModel.postSendOrder(listSendOrders())
        printOrder()
        val action: NavDirections = OrderPanelFragDirections.nextActionMain()
        findNavController().navigate(action)
    }
    private fun listSendOrders() : SendOrdersModel {
        val datoLoginExitoso = viewModel.LoginUserResponse.value
        val idTable = OrderPanelFragArgs.fromBundle(requireArguments()).mesa
        val idZona = OrderPanelFragArgs.fromBundle(requireArguments()).idZone

        var importeTotalLista = 0.0
        var TotalIgv= 0.0
        var subTotalLista= 0.0

        fun listDetailOrder() : MutableList<DetalleModel> {
            val listDetailOrder : MutableList<DetalleModel> = mutableListOf()
            val listOrdersFilter : MutableList<ListOrdersModel> = mutableListOf()
            var secuencia = 0

            listOrdersFilter.clear()
            for(i in listOrders.indices){
                var lt = listOrders[i]
                var conteo = 0
                var pos = 0
                for (e in listOrdersFilter.indices){
                    if (listOrders[i].namePlato == listOrdersFilter[e].namePlato){
                        conteo += 1
                    }
                    if (conteo == 1){
                        pos = e
                        break
                    }
                }

                if (conteo == 0){
                    listOrdersFilter.add(listOrders[i])
                }else{
                    listOrdersFilter[pos] = ListOrdersModel(
                        cantidad = lt.cantidad+listOrdersFilter[pos].cantidad,
                        namePlato = lt.namePlato,
                        categoria = lt.categoria,
                        precio = lt.precio,
                        precioTotal =  ((lt.cantidad+listOrdersFilter[pos].cantidad)*lt.precio),
                        observacion = lt.observacion,
                        estadoPedido =lt.estadoPedido,
                        idProducto = lt.idProducto,
                        camanda = lt.camanda,
                        igv = utils().priceIGV(((lt.cantidad+listOrdersFilter[pos].cantidad)*lt.precio)),
                        psigv = utils().priceSubTotal(((lt.cantidad+listOrdersFilter[pos].cantidad)*lt.precio)),
                        flag_color = lt.flag_color)
                }
            }
            importeTotalLista = listOrdersFilter.sumOf { t -> t.precioTotal }
            TotalIgv = listOrdersFilter.sumOf { t -> t.igv }
            subTotalLista= listOrdersFilter.sumOf { t -> t.psigv}

            listOrdersFilter.forEach {
                listDetailOrder.add(
                    DetalleModel(
                        iD_PEDIDO= 0,
                        iD_PRODUCTO= it.idProducto,
                        cantidad= it.cantidad,
                        nombre= it.namePlato,
                        precio= it.precio,
                        descuento=0,
                        igv= it.igv,
                        importe= it.precioTotal,
                        canT_DESPACHADA=0,
                        canT_FACTURADA=0,
                        observacion= it.observacion,
                        secuencia= secuencia,
                        preciO_ORIGINAL= it.precio,
                        tipo="1",
                        importE_DSCTO=0,
                        afectO_IGV="1",
                        comision=0,
                        iD_PRESUPUESTO=0,
                        cdG_SERV="",
                        flaG_C="0",
                        flaG_P="",
                        flaG_COLOR= "0",
                        noM_UNIDAD="",
                        comanda= it.camanda,
                        mozo= datoLoginExitoso!!.nombreUsuario,
                        unidad="0001",      //***
                        codigO_BARRA="",
                        poR_PERCEPCION=0,
                        percepcion=0,
                        valoR_VEN= it.psigv,
                        uniD_VEN="",
                        fechA_VEN= utils().getFecha(),
                        factoR_CONVERSION=1, //**
                        cdG_KIT="",
                        swT_PIGV="S",      //**
                        swT_PROM="N",
                        canT_KIT=0,
                        swT_DCOM="N",     //**
                        swT_SABOR="0",
                        swT_FREE="N",      //**
                        noM_IMP="",
                        seC_PROD=0,
                        poR_DETRACCION=0,
                        detraccion=0,
                        usuariO_ANULA="",
                        fechA_ANULA= utils().getFecha(),
                        margen=0,
                        importE_MARGEN=0,
                        costO_ADIC=0)
                )
                secuencia += 1
            }
            return listDetailOrder
        }
        fun headOrder() : SendOrdersModel {
            val sendOrders = SendOrdersModel(
                iD_PEDIDO=0,
                numerO_PEDIDO="",
                noM_MON="",
                smB_MON="",
                conD_PAGO=datoLoginExitoso!!.cdgpago ?: "",
                persona="",
                ruc="",
                freC_DIAS="",
                codigO_VENDEDOR= datoLoginExitoso.cdG_VENDEDOR ,
                codigO_CPAGO= datoLoginExitoso.cdgpago ,
                codigO_MONEDA= datoLoginExitoso.cdgmoneda,
                fechA_PEDIDO=utils().getFecha(),
                numerO_OCLIENTE="",
                importE_STOT=subTotalLista,
                importE_IGV=TotalIgv,
                importE_DESCUENTO=0,
                importE_TOTAL=importeTotalLista,
                porcentajE_DESCUENTO=0,
                porcentajE_IGV= datoLoginExitoso.poR_IGV.toInt(),
                observacion = "",
                serie="",
                estado="0001",
                iD_CLIENTE=datoLoginExitoso.iD_CLIENTE,
                importE_ISC=0,
                usuariO_CREACION=datoLoginExitoso.usuariocreacion,
                usuariO_AUTORIZA=datoLoginExitoso.usuarioautoriza,
                fechA_CREACION=utils().getFecha(),
                fechA_MODIFICACION=utils().getFecha(),
                codigO_EMPRESA=datoLoginExitoso.codigO_EMPRESA,
                codigO_SUCURSAL=datoLoginExitoso.sucursal,
                valoR_VENTA=subTotalLista,
                iD_CLIENTE_FACTURA=datoLoginExitoso.iD_CLIENTE,
                codigO_VENDEDOR_ASIGNADO="",
                fechA_PROGRAMADA=utils().getFecha(),
                facturA_ADELANTADA="",
                contacto="",
                emaiL_CONTACTO="",
                lugaR_ENTREGA="",
                iD_COTIZACION=datoLoginExitoso.iD_COTIZACION.toInt(),
                comision=0,
                puntO_VENTA="",
                redondeo=datoLoginExitoso.redondeo,
                validez="",
                motivo="",
                correlativo="",
                centrO_COSTO="",
                tipO_CAMBIO=0,
                sucursal=datoLoginExitoso.sucursal,
                mesa= idTable,
                piso= idZona,
                detalle= listDetailOrder(),
            )
            return sendOrders
        }
        return headOrder()
    }
    //----- ON CLICK ITEM
    private fun onItemDatosCategory(dataclassCategory: CategoryModel) {
        val nameCategory = dataclassCategory.nameCategoria
        viewModel.getDishData(nameCategory)
    }
    private fun onItemDatosDish(dataclassDish: DishModel) {
        fun setAddDish(){
            val searchcoincidence = searchCoincidence(dataclassDish.iD_PRODUCTO)
            val action = searchcoincidence[0]
            val pos = searchcoincidence[1]
            if (action == 0) {
                listOrders.add(
                    ListOrdersModel(
                        cantidad= 1,
                        namePlato= dataclassDish.nombre,
                        categoria= dataclassDish.codigo,
                        precio= dataclassDish.preciO_VENTA,
                        precioTotal= dataclassDish.preciO_VENTA,
                        observacion= "",
                        estadoPedido= "PENDIENTE",
                        idProducto= dataclassDish.iD_PRODUCTO,
                        camanda= dataclassDish.comanda,
                        igv= utils().priceIGV(dataclassDish.preciO_VENTA),
                        psigv= utils().priceSubTotal(dataclassDish.preciO_VENTA),
                        flag_color= 0,
                    )
                )
            }else {
                val cantidad = listOrders[pos].cantidad + 1
                val precioTotal = listOrders[pos].precio * cantidad
                listOrders[pos] =
                    ListOrdersModel(
                        cantidad= cantidad,
                        namePlato= listOrders[pos].namePlato,
                        categoria= listOrders[pos].categoria,
                        precio= listOrders[pos].precio,
                        precioTotal= precioTotal,
                        observacion= listOrders[pos].observacion,
                        estadoPedido= "PENDIENTE",
                        idProducto= listOrders[pos].idProducto,
                        camanda= listOrders[pos].camanda,
                        igv= utils().priceIGV(listOrders[pos].precio),
                        psigv= utils().priceSubTotal(listOrders[pos].precio),
                        flag_color= 0,
                    )
            }
            orderAdapter.notifyDataSetChanged()
        }
        setAddDish()
    }
    private fun onClickItemListOrdersDiminish(position: Int) {
        val data = listOrders[position]
        if (data.cantidad>1){
            data.cantidad = data.cantidad - 1
            orderAdapter.notifyItemChanged(position)
        }
    }
    private fun onClickItemListOrdersIncrease(position: Int) {
        val data = listOrders[position]
            data.cantidad = data.cantidad + 1
            orderAdapter.notifyItemChanged(position)
    }
    private fun onClickItemListOrdersObservation(position: Int) {

    }
    //----- UTILS
    private fun searchCoincidence(idProducto:Int): List<Int> {
        //-------------Evalua POSICION Y ACCION DE AGREGAR-------------------
        var action = 0
        var pos = -1

        for (i in listOrders.indices) {
            if (listOrders[i].idProducto == idProducto && listOrders[i].estadoPedido=="PENDIENTE") {
                action += 1
            }
            if (action == 1) {
                pos = i
                println("posicion: $pos")
                break
            }
        }

        return listOf(action, pos)
    }
    private fun printOrder(){
        val listaCodComanda: ArrayList<String> = ArrayList()
        var idPedido = ""
        viewModel.responseOrder.observe(viewLifecycleOwner){ it ->
            idPedido = it.iD_PEDIDO.toString()
            viewModel.getOrder(idPedido)
            it.detalle.forEach { detailModel ->
                listaCodComanda.add(detailModel.comanda)
            }
        }
        viewModel.ListComanda.observe(viewLifecycleOwner){ r->
            val listaDetalleComanda = ArrayList<OrderDetailModel>()
            val listaComanda = ArrayList<OrderModel>()
            val impComanda = PrintOrder()

            r.forEach { cabezera ->
                cabezera.detalle.forEach { detalleComanda ->
                    for (i in listOrders.indices){
                        if(detalleComanda.producto == listOrders[i].namePlato && listOrders[i].estadoPedido == "PENDIENTE"){
                            listaDetalleComanda.add(OrderDetailModel(
                                detalleComanda.iD_PRODUCTO,
                                detalleComanda.producto,
                                listOrders[i].cantidad,
                                detalleComanda.precio,
                                detalleComanda.precio*listOrders[i].cantidad,
                                listOrders[i].observacion,
                                detalleComanda.noM_IMP,
                                secuencia = i,
                            ))
                        }
                    }
                }
                listaComanda.add(OrderModel(cabezera.numerO_PEDIDO,cabezera.destino,cabezera.zona,cabezera.mesa,cabezera.mesero,cabezera.rutacomanda,cabezera.fechayhora,listaDetalleComanda))
            }

            var init = " "
            listaCodComanda.forEach {
                if (init != it){
                    cambiarColorComandado(it,idPedido.toInt())
                    init = it
                }
            }

            listaComanda.forEach {
                impComanda.printTcp(it.rutacomanda,9100,it)
            }

        }

    }
    private fun cambiarColorComandado(comada:String,ippedido:Int) {
        viewModel.putUpdateColorOrder(comada,ippedido)
    }
    //----- START COMPONENT
    private fun initCategoryRecyclerview() {
        binding.rvCategoria.layoutManager = GridLayoutManager(activity,2, RecyclerView.HORIZONTAL,false)
        categoryAdapter = CategoryAdapter(listaCategories) { dataclassCategory -> onItemDatosCategory(dataclassCategory) }
        binding.rvCategoria.adapter = categoryAdapter
    }
    private fun initDishRecyclerview() {
        binding.rvPlatillo.layoutManager = GridLayoutManager(activity,3, RecyclerView.VERTICAL,false)
        dishAdapter = DishAdapter(listDish) { dataclassDish -> onItemDatosDish(dataclassDish) }
        binding.rvPlatillo.adapter = dishAdapter
    }
    private fun initListOrdersRecyclerview() {
        binding.rvPedido.layoutManager = LinearLayoutManager(activity)
        orderAdapter = OrderAdapter(
            data = listOrders,
            itemClickIncrease = { position -> onClickItemListOrdersIncrease(position) },
            itemClickDiminish = { position -> onClickItemListOrdersDiminish(position) },
            itemClickObservation = { position-> onClickItemListOrdersObservation(position) }
        )
        binding.rvPedido.adapter = orderAdapter
    }
    //----- GET DATA
    private fun getListCategory() {
        viewModel.listCategories.observe(viewLifecycleOwner){ it ->
            categoryAdapter.setItems(it)
            val inicio = it[0].nameCategoria
            viewModel.getDishData(inicio)
        }
    }
    private fun getListDish() {
        viewModel.listDish.observe(viewLifecycleOwner){ it ->
            dishAdapter.setItems(it)
        }
    }
    private fun getListOrders() {
        viewModel.listOrders.observe(viewLifecycleOwner){ it ->
            orderAdapter.setItems(it)
        }
    }
    //----- SWIPE DELETE
    private fun enableSwipeToDelete() {
        val itemswipe = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                if (listOrders[viewHolder.bindingAdapterPosition].estadoPedido == "PENDIENTE"){
                    listOrders.removeAt(viewHolder.bindingAdapterPosition)
                }
                orderAdapter.notifyDataSetChanged()
            }
        }
        val itemTouchhelper = ItemTouchHelper(itemswipe)
        itemTouchhelper.attachToRecyclerView(binding.rvPedido)
    }
    //----- TOAS ERROR
    private fun messageState() {
        viewModel.message.observe(viewLifecycleOwner){ it ->
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
    }















}