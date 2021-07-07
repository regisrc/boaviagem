package com.example.boaviagem.ui.gasto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.boaviagem.R
import com.example.boaviagem.controller.GastoController
import com.example.boaviagem.data.model.Gasto
import com.example.boaviagem.data.model.TipoViagemEnum
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GastoAdapter(val context: Context): RecyclerView.Adapter<GastoAdapter.ViewHolder>() {
    private var gastoList: MutableList<Gasto> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipo = itemView.findViewById<TextView>(R.id.textViewTipoGastoResult)
        val valor = itemView.findViewById<TextView>(R.id.textViewValorGastoResult)
        val data = itemView.findViewById<TextView>(R.id.textViewDataGastoResult)
        val desc = itemView.findViewById<TextView>(R.id.textViewDescGastoResult)
        val local = itemView.findViewById<TextView>(R.id.textViewLocalGastoResult)
        var id :Long = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.gasto_card, parent, false))
    }

    override fun getItemCount() = gastoList.size

    fun setList(list:List<Gasto>) {
        gastoList = list.toMutableList()

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id = gastoList[position].id
        holder.tipo.text = gastoList[position].tipo
        holder.valor.text = gastoList[position].valor.toString()
        holder.data.text = gastoList[position].data.getFormattedDate()
        holder.desc.text = gastoList[position].descricao
        holder.local.text = gastoList[position].local
    }

    fun Date.getFormattedDate():String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        return dateFormat.format(this)
    }
}

