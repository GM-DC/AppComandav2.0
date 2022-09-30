package com.example.appcomandav20.view.ui.PanelPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.databinding.FragMainPanelBinding
import com.example.appcomandav20.domain.model.*
import com.example.appcomandav20.view.ui.PanelPrincipal.adapter.*
import com.example.apppedido.domain.Model.DetalleModel
import com.example.apppedido.domain.Model.SendOrdersModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime


@AndroidEntryPoint
class MainPanelFrag : Fragment() {
    private var _binding: FragMainPanelBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainPanelViewModel

    private lateinit var zoneAdapter: ZoneAdapter
    private val listaZona = mutableListOf<ZoneModel>()

    private lateinit var tableAdapter: TableAdapter
    private val listaTable = mutableListOf<TableModel>()

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
        _binding = FragMainPanelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initZoneRecyclerview()
        initTableRecyclerview()
        initCategoryRecyclerview()
        initDishRecyclerview()
        initListOrdersRecyclerview()
        getListZone()
        getListTable()
        getListCategory()
        getListDish()
        getListOrders()
        eventsHandlers()
    }

    private fun eventsHandlers() {
        binding.btEnviarComanda.setOnClickListener{ sendOrders() }
    }

    private fun sendOrders() {
        viewModel.postSendOrder(listSendOrders())
    }

    private fun listSendOrders() : SendOrdersModel {
        fun listDetailOrder() : MutableList<DetalleModel> {
            val listDetailOrder : MutableList<DetalleModel> = mutableListOf()
            listDetailOrder.add(DetalleModel(
                iD_PEDIDO= 0,
                iD_PRODUCTO= 0,
                cantidad= 0,
                nombre= "",
                precio= 0.0,
                descuento=0,
                igv= 0.0,
                importe= 0.0,
                canT_DESPACHADA=0,
                canT_FACTURADA=0,
                observacion= "",
                secuencia= 0,
                preciO_ORIGINAL= 0.0,
                tipo="1",            //***
                importE_DSCTO=0,
                afectO_IGV="1",       //***
                comision=0,
                iD_PRESUPUESTO=0,
                cdG_SERV="",
                flaG_C="0",          //***
                flaG_P="",
                flaG_COLOR= "",
                noM_UNIDAD="",
                comanda= "",
                mozo= "",
                unidad="0001",      //***
                codigO_BARRA="",
                poR_PERCEPCION=0,
                percepcion=0,
                valoR_VEN= 0.0,
                uniD_VEN="",
                fechA_VEN= "",
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
                fechA_ANULA= "",
                margen=0,
                importE_MARGEN=0,
                costO_ADIC=0)
            )
            return listDetailOrder
        }
        fun headOrder() : SendOrdersModel {
            val sendOrders = SendOrdersModel(
                iD_PEDIDO=0,
                numerO_PEDIDO="",
                noM_MON="",
                smB_MON="",
                conD_PAGO="",
                persona="",
                ruc="",
                freC_DIAS="",
                codigO_VENDEDOR="",
                codigO_CPAGO="",
                codigO_MONEDA="",
                fechA_PEDIDO="",
                numerO_OCLIENTE="",
                importE_STOT=0.0,
                importE_IGV=0.0,
                importE_DESCUENTO=0,
                importE_TOTAL=0.0,
                porcentajE_DESCUENTO=0,
                porcentajE_IGV=0,
                observacion = "",
                serie="",
                estado="0001",
                iD_CLIENTE=0,
                importE_ISC=0,
                usuariO_CREACION="",
                usuariO_AUTORIZA="",
                fechA_CREACION="",
                fechA_MODIFICACION="",
                codigO_EMPRESA="",
                codigO_SUCURSAL="",
                valoR_VENTA=0.0,
                iD_CLIENTE_FACTURA=0,
                codigO_VENDEDOR_ASIGNADO="",
                fechA_PROGRAMADA="",
                facturA_ADELANTADA="",
                contacto="",
                emaiL_CONTACTO="",
                lugaR_ENTREGA="",
                iD_COTIZACION=0,
                comision=0,
                puntO_VENTA="",
                redondeo="",
                validez="",
                motivo="",
                correlativo="",
                centrO_COSTO="",
                tipO_CAMBIO=0,
                sucursal="",
                mesa="",
                piso="",
                detalle=listDetailOrder(),
            )
            return sendOrders
        }
        return headOrder()
    }

    private fun getListZone() {
        viewModel.listZones.observe(viewLifecycleOwner){ it ->
            zoneAdapter.setItems(it)
            val inicio = it[0].idZona
            viewModel.getTableData(inicio)
        }
    }
    private fun getListTable() {
        viewModel.listTable.observe(viewLifecycleOwner){ it ->
            tableAdapter.setItems(it)
        }
    }
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

    private fun initZoneRecyclerview() {
        binding.rvZona2.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL,false)
        zoneAdapter = ZoneAdapter(listaZona) { dataclassZonas -> onItemDatosZonas(dataclassZonas) }
        binding.rvZona2.adapter = zoneAdapter
    }
    private fun initTableRecyclerview() {
        binding.rvMesa2.layoutManager = GridLayoutManager(activity,2, RecyclerView.HORIZONTAL,false)
        tableAdapter = TableAdapter(listaTable) { dataclassTable -> onItemDatosTable(dataclassTable) }
        binding.rvMesa2.adapter = tableAdapter
    }
    private fun initCategoryRecyclerview() {
        binding.rvCategoria.layoutManager = GridLayoutManager(activity,2, RecyclerView.HORIZONTAL,false)
        categoryAdapter = CategoryAdapter(listaCategories) { dataclassCategory -> onItemDatosCategory(dataclassCategory) }
        binding.rvCategoria.adapter = categoryAdapter
    }
    private fun initDishRecyclerview() {
        binding.rvPlatillo.layoutManager = GridLayoutManager(activity,2, RecyclerView.HORIZONTAL,false)
        dishAdapter = DishAdapter(listDish) { dataclassDish -> onItemDatosDish(dataclassDish) }
        binding.rvPlatillo.adapter = dishAdapter
    }
    private fun initListOrdersRecyclerview() {
        binding.rvPedido.layoutManager = LinearLayoutManager(activity)
        orderAdapter = OrderAdapter(listOrders) { dataclassListOrders -> onItemDatosListOrders(dataclassListOrders) }
        binding.rvPedido.adapter = orderAdapter
    }

    private fun onItemDatosListOrders(dataclassListOrders: ListOrdersModel) {

    }

    private fun onItemDatosZonas(dataclassZonas: ZoneModel) {
        val idZona = dataclassZonas.idZona
        viewModel.getTableData(idZona)
        binding.tvZone.text = dataclassZonas.nombreZonas
    }

    private fun onItemDatosTable(dataclassTable: TableModel) {
        binding.tvTable.text = "MESA: "+dataclassTable.idMesa.toString()
        viewModel.getOrderFulfilled(dataclassTable.idPedido.toString())
    }

    private fun onItemDatosCategory(dataclassCategory: CategoryModel) {
        val nameCategory = dataclassCategory.nameCategoria
        viewModel.getDishData(nameCategory)
    }

    private fun onItemDatosDish(dataclassDish: DishModel) {

    }

}