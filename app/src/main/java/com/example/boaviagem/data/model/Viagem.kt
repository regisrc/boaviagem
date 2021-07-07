package com.example.boaviagem.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Usuario::class, parentColumns = ["id"], childColumns = ["usuarioId"], onDelete = ForeignKey.CASCADE)])
data class Viagem(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val destino: String,
        val dataChegada: Date,
        val dataPartida: Date,
        val orcamento: BigDecimal,
        val tipo: String,
        val usuarioId: Long
) { var gastoTotal: BigDecimal = BigDecimal(0) }