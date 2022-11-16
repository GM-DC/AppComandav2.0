package com.example.appcomandav20.view.ui.PanelPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.R
import com.example.appcomandav20.databinding.FragMainPanelBinding
import com.example.appcomandav20.domain.model.*
import com.example.appcomandav20.util.utils
import com.example.appcomandav20.view.ui.PanelPrincipal.adapter.*
import com.example.apppedido.domain.Model.DetalleModel
import com.example.apppedido.domain.Model.SendOrdersModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainPanelFrag : Fragment() {
    private var _binding: FragMainPanelBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainPanelViewModel

    private lateinit var zoneAdapter: ZoneAdapter
    private val listaZona = mutableListOf<ZoneModel>()

    private lateinit var tableAdapter: TableAdapter
    private val listaTable = mutableListOf<TableModel>()

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

        getListZone()
        getListTable()

        eventsHandlers()
    }

    //----- EVENTS CLICK
    private fun eventsHandlers() {

    }
    //----- GET DATA
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
    //----- STAR COMPONENTS
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
    //----- ON CLICK ITEM
    private fun onItemDatosZonas(dataclassZonas: ZoneModel) {
        val idZona = dataclassZonas.idZona
        viewModel.cancelarCorrutine()
        viewModel.getTableData(idZona)
    }
    private fun onItemDatosTable(dataclassTable: TableModel) {
        val datoLoginExitoso = viewModel.LoginUserResponse.value
        viewModel.cancelarCorrutine()
        viewModel.getOrderFulfilled(dataclassTable.idPedido.toString())
        val idTable = dataclassTable.idMesa.toString()
        val idZone = dataclassTable.idZona
        val nameWaiter = dataclassTable.NombreMozo ?: ""
        var nameZona = ""
        var idOrder = ""

        for (i in listaZona.indices){
            if(listaZona[i].idZona==dataclassTable.idZona){
                nameZona = listaZona[i].nombreZonas
                break
            }
        }

        if (dataclassTable.idPedido.isNullOrEmpty()){
            idOrder = ""
        }else{
            idOrder = dataclassTable.idPedido
        }

        viewModel.putUpdateStateTable(idZone,idTable.toInt(),"O", datoLoginExitoso!!.usuario)
        val action:NavDirections = MainPanelFragDirections.nextActionOrder().setMesa(idTable).setZona(nameZona).setNameWaiter(nameWaiter).setIdZone(idZone).setIdOrder(idOrder)
        findNavController().navigate(action)
    }
}