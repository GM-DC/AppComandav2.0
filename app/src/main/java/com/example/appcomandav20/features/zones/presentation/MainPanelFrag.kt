package com.example.appcomandav20.features.zones.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.databinding.FragMainPanelBinding
import com.example.appcomandav20.features.zones.domain.model.TableModel
import com.example.appcomandav20.features.zones.domain.model.ZoneModel
import com.example.appcomandav20.features.zones.presentation.adapter.TableAdapter
import com.example.appcomandav20.features.zones.presentation.adapter.ZoneAdapter
import com.example.appcomandav20.util.Constants.Companion.DATA_TABLE
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
        binding.rvZona2.layoutManager = GridLayoutManager(activity,2, RecyclerView.VERTICAL,false)
        zoneAdapter = ZoneAdapter(listaZona) { dataclassZonas -> onItemDatosZonas(dataclassZonas) }
        binding.rvZona2.adapter = zoneAdapter
    }
    private fun initTableRecyclerview() {
        binding.rvMesa2.layoutManager = GridLayoutManager(activity,3, RecyclerView.VERTICAL,false)
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
        DATA_TABLE = dataclassTable.copy()
        viewModel.cancelarCorrutine()
        //viewModel.getOrderFulfilled(DATA_TABLE.idPedido.toString())
        val idTable = dataclassTable.idMesa.toString()
        val idZone = dataclassTable.idZona
        val nameWaiter = dataclassTable.NombreMozo ?: " "
        var nameZona = ""
        val idOrder = if (dataclassTable.idPedido.isNullOrEmpty()) ""
        else dataclassTable.idPedido

        for (i in listaZona.indices){
            if(listaZona[i].idZona==dataclassTable.idZona){
                nameZona = listaZona[i].nombreZonas
                break
            }
        }

        val action:NavDirections = MainPanelFragDirections.nextActionOrder()
            .setMesa(idTable).setZona(nameZona).setNameWaiter(nameWaiter).setIdZone(idZone).setIdOrder(idOrder)
        findNavController().navigate(action)
    }
}