package com.example.boaviagem

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.boaviagem.data.model.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM Usuario WHERE email = :email AND password = :password")
    suspend fun getUsuario(email: String, password: String): Usuario?

    @Insert
    suspend fun insert(usuario: Usuario)
}