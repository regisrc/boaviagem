package com.example.boaviagem.ui.home.ui.newViagem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.boaviagem.R
import com.example.boaviagem.controller.ViagemController
import com.example.boaviagem.data.model.TipoViagemEnum
import com.example.boaviagem.data.model.Viagem
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*

class NewViagemFragment : Fragment() {

    private lateinit var controller: ViagemController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val editDestino = root.findViewById<EditText>(R.id.editTextDestino)
        val editTipo = root.findViewById<Spinner>(R.id.editTextTipo)
        val editPartida = root.findViewById<EditText>(R.id.editTextPartida)
        val editChegada = root.findViewById<EditText>(R.id.editTextChegada)
        val editOrcamento = root.findViewById<EditText>(R.id.editTextOrcamento)
        val button = root.findViewById<Button>(R.id.buttonFragmentViagem)
        editTipo.adapter = ArrayAdapter<TipoViagemEnum>(requireContext(), R.layout.support_simple_spinner_dropdown_item, TipoViagemEnum.values())

        var itemTipo: TipoViagemEnum = TipoViagemEnum.Lazer

        editTipo.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                itemTipo = TipoViagemEnum.values()[p2]
            }
        }

        controller = ViagemController(root.context)

        button.setOnClickListener {
            val destino = editDestino.text.toString()
            val tipo = itemTipo.toString()
            val partida = editPartida.text.toString()
            val chegada = editChegada.text.toString()
            val orcamento = editOrcamento.text.toString()

            val id = activity?.intent?.extras?.getLong("id")

            val viagem = Viagem(destino = destino, dataChegada = Date(chegada), dataPartida = Date(partida), tipo = tipo, orcamento = BigDecimal(orcamento), usuarioId = id!!)

            lifecycleScope.launch {
                controller.cadastrarViagem(viagem)
            }
        }

        return root
    }
}