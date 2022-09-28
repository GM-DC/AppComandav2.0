package com.example.appcomandav20.view.ui.PanelPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcomandav20.databinding.FragMainPanelBinding
import com.example.appcomandav20.domain.model.*
import com.example.appcomandav20.view.adapter.CategoryAdapter
import com.example.appcomandav20.view.adapter.DishAdapter
import com.example.appcomandav20.view.adapter.TableAdapter
import com.example.appcomandav20.view.adapter.ZoneAdapter
import com.example.appcomandav20.view.ui.viewmodels.MainPanelViewModel
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

    private lateinit var categoryAdapter: CategoryAdapter
    private val listaCategories = mutableListOf<CategoryModel>()

    private lateinit var dishAdapter: DishAdapter
    private val listDish = mutableListOf<DishModel>()


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
        getListZone()
        getListTable()
        getListCategory()
        getListDish()
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

    private fun onItemDatosDish(dataclassDish: DishModel) {

    }

    private fun onItemDatosCategory(dataclassCategory: CategoryModel) {
        val nameCategory = dataclassCategory.nameCategoria
        println(nameCategory)
        viewModel.getDishData(nameCategory)
    }

    private fun onItemDatosZonas(dataclassZonas: ZoneModel) {
        val idZona = dataclassZonas.idZona
        viewModel.getTableData(idZona)
    }

    private fun onItemDatosTable(dataclassTable: TableModel) {

    }

}