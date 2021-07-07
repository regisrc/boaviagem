package com.example.boaviagem.ui.gasto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.example.boaviagem.R
import com.example.boaviagem.controller.GastoController
import com.example.boaviagem.controller.ViagemController
import com.example.boaviagem.data.model.Gasto
import com.example.boaviagem.data.model.TipoGastoEnum
import com.example.boaviagem.data.model.TipoViagemEnum
import com.example.boaviagem.data.model.Viagem
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*

class NewGastoActivity : AppCompatActivity() {

    private lateinit var controller: GastoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_gasto)

        val editTextValor = findViewById<EditText>(R.id.editTextValor)
        val editTextTipoGasto = findViewById<Spinner>(R.id.editTextTipoGasto)
        val editTextDataGasto = findViewById<EditText>(R.id.editTextDataGasto)
        val editTextDescGasto = findViewById<EditText>(R.id.editTextDescGasto)
        val editTextLocalGasto = findViewById<EditText>(R.id.editTextLocalGasto)
        val button = findViewById<Button>(R.id.buttonGastoActivity)
        editTextTipoGasto.adapter = ArrayAdapter<TipoGastoEnum>(this, R.layout.support_simple_spinner_dropdown_item, TipoGastoEnum.values())

        var itemTipo: TipoGastoEnum = TipoGastoEnum.Alimentacao

        editTextTipoGasto.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                itemTipo = TipoGastoEnum.values()[p2]
            }
        }

        controller = GastoController(this)

        button.setOnClickListener {
            val valor = editTextValor.text.toString()
            val tipo = itemTipo.toString()
            val data = editTextDataGasto.text.toString()
            val desc = editTextDescGasto.text.toString()
            val local = editTextLocalGasto.text.toString()

            val id = intent?.extras?.getLong("id")

            val gasto = Gasto(tipo = tipo, valor = BigDecimal(valor), data = Date(data), descricao = desc, local = local, viagemId = id!!)

            lifecycleScope.launch {
                controller.cadastrarGasto(gasto)
            }
        }
    }
}