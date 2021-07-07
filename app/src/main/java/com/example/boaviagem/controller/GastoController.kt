package com.example.boaviagem.controller

import android.content.Context
import com.example.boaviagem.UsuarioRoomDatabase
import com.example.boaviagem.data.model.Gasto
import com.example.boaviagem.data.model.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigDecimal

class GastoController(
    context: Context
) {

    private val gastoDao = UsuarioRoomDatabase.getDatabase(context).gastoDao()

    suspend fun cadastrarGasto(gasto: Gasto) = withContext(Dispatchers.IO) {
        gastoDao.insert(gasto)
    }

    suspend fun getGastos(viagemId: Long): List<Gasto>? = withContext(Dispatchers.IO) {
        gastoDao.getGastos(viagemId)
    }

    suspend fun getGastosTotal(viagemId: Long): BigDecimal? = withContext(Dispatchers.IO) {
        gastoDao.getGastosTotal(viagemId)
    }

    suspend fun getGasto(gastoId: Long): Gasto? = withContext(Dispatchers.IO) {
        gastoDao.getGasto(gastoId)
    }

    suspend fun delGasto(gasto: Long) = withContext(Dispatchers.IO) {
        val gasto = getGasto(gasto)

        gastoDao.delete(gasto!!)
    }
}