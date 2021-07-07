package com.example.boaviagem.controller

import android.content.Context
import com.example.boaviagem.UsuarioDao
import com.example.boaviagem.UsuarioRoomDatabase
import com.example.boaviagem.data.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioController(
    context: Context
) {

    private val usuarioDao = UsuarioRoomDatabase.getDatabase(context).usuarioDao()

    suspend fun cadastrarUsuario(usuario: Usuario) = withContext(Dispatchers.IO) {
        usuarioDao.insert(usuario)
    }

    suspend fun logar(email: String, password: String): Usuario? = withContext(Dispatchers.IO) {
        usuarioDao.getUsuario(email, password)
    }
}