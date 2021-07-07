package com.example.boaviagem.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Viagem::class, parentColumns = ["id"], childColumns = ["viagemId"], onDelete = ForeignKey.CASCADE)])
data class Gasto(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val tipo: String,
        val valor: BigDecimal,
        val data: Date,
        val descricao: String,
        val local: String,
        val viagemId: Long
)