package com.example.boaviagem.ui.gasto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.controller.GastoController
import com.example.boaviagem.ui.home.ui.home.HomeAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.math.BigDecimal

class GastoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gastoController: GastoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gasto)

        val id = intent?.extras?.getLong("id")
        val floatButton: FloatingActionButton = findViewById(R.id.floatingActionButton)

        floatButton.setOnClickListener {
            val intent = Intent(this, NewGastoActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()

        gastoController = GastoController(this)
        val id = intent?.extras?.getLong("id")
        recyclerView = findViewById(R.id.rv_gasto_viagem)
        val adapter = GastoAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val gastos = gastoController.getGastos(id!!)

            adapter.setList(gastos!!)
        }
    }
}