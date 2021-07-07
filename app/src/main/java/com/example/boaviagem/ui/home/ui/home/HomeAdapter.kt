package com.example.boaviagem.ui.home.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.controller.GastoController
import com.example.boaviagem.data.model.TipoViagemEnum
import com.example.boaviagem.data.model.Viagem
import com.example.boaviagem.ui.gasto.GastoActivity
import com.example.boaviagem.ui.home.HomeActivity
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter(val context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var viagemList: MutableList<Viagem> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destino = itemView.findViewById<TextView>(R.id.textViewDestinoResult)
        val dataChegada = itemView.findViewById<TextView>(R.id.textViewDataChegadaResult)
        val dataPartida = itemView.findViewById<TextView>(R.id.textViewDataPartidaResult)
        val orcamento = itemView.findViewById<TextView>(R.id.textViewOrcamentoResult)
        val gastoTotal = itemView.findViewById<TextView>(R.id.textViewGastoTotalResult)
        val tipo = itemView.findViewById<TextView>(R.id.textViewTipoResult)
        val image = itemView.findViewById<ImageView>(R.id.imageViewCard)
        var id: Long = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, GastoActivity::class.java)
                intent.putExtra("id", id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_card, parent, false))
    }

    override fun getItemCount() = viagemList.size

    fun setList(list:List<Viagem>) {
        viagemList = list.toMutableList()

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.destino.text = viagemList[position].destino
        holder.dataChegada.text = viagemList[position].dataChegada.getFormattedDate()
        holder.dataPartida.text = viagemList[position].dataPartida.getFormattedDate()
        holder.orcamento.text = viagemList[position].orcamento.toString()
        holder.gastoTotal.text = viagemList[position].gastoTotal.toString()
        holder.tipo.text = viagemList[position].tipo.toString()
        holder.image.setImageResource(R.drawable.ic_business)

        holder.id = viagemList[position].id

        if(viagemList[position].tipo == TipoViagemEnum.Lazer.toString())
            holder.image.setImageResource(R.drawable.ic_lounge)
    }

    fun Date.getFormattedDate():String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        return dateFormat.format(this)
    }
}

