package com.example.boaviagem.controller

import android.content.Context
import com.example.boaviagem.UsuarioDao
import com.example.boaviagem.UsuarioRoomDatabase
import com.example.boaviagem.data.model.Usuario
import com.example.boaviagem.data.model.Viagem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ViagemController(
    context: Context
) {

    private val viagemDao = UsuarioRoomDatabase.getDatabase(context).viagemDao()

    suspend fun cadastrarViagem(viagem: Viagem) = withContext(Dispatchers.IO) {
        viagemDao.insert(viagem)
    }

    suspend fun getViagens(usuarioId: Long): List<Viagem>? = withContext(Dispatchers.IO) {
        viagemDao.getViagem(usuarioId)
    }
}