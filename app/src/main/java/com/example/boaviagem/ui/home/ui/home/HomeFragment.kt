package com.example.boaviagem.ui.home.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.controller.GastoController
import com.example.boaviagem.controller.ViagemController
import kotlinx.coroutines.launch
import java.math.BigDecimal

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var controller: ViagemController
    private lateinit var gastoController: GastoController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        controller = ViagemController(requireContext())
        gastoController = GastoController(requireContext())
        recyclerView = view.findViewById(R.id.rv_home_viagem)

    }

    override fun onResume() {
        super.onResume()

        val id = activity?.intent?.extras?.getLong("id")

        val adapter = HomeAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val viagens = controller.getViagens(id!!)

            viagens?.forEach {
                val gasto: BigDecimal? = gastoController.getGastosTotal(it.id)

                it.gastoTotal = BigDecimal(0)

                if (gasto != null)
                    it.gastoTotal = gasto
            }

            adapter.setList(viagens!!)
        }
    }
}