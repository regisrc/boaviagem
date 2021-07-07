package com.example.boaviagem

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.boaviagem.data.model.Gasto
import com.example.boaviagem.data.model.Usuario
import com.example.boaviagem.data.model.Viagem
import java.math.BigDecimal

@Dao
interface GastoDao {

    @Query("SELECT * FROM Gasto WHERE viagemId = :viagemId")
    suspend fun getGastos(viagemId: Long): List<Gasto>?

    @Insert
    suspend fun insert(gasto: Gasto)

    @Delete
    suspend fun delete(gasto: Gasto)

    @Query("SELECT sum(valor) FROM Gasto WHERE viagemId = :viagemId")
    suspend fun getGastosTotal(viagemId: Long): BigDecimal?

    @Query("SELECT * FROM Gasto WHERE id = :gastoId")
    suspend fun getGasto(gastoId: Long): Gasto?
}