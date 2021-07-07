package com.example.boaviagem

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.boaviagem.data.model.Usuario
import com.example.boaviagem.data.model.Viagem

@Dao
interface ViagemDao {

    @Query("SELECT * FROM Viagem WHERE usuarioId = :usuarioId")
    suspend fun getViagem(usuarioId: Long): List<Viagem>?

    @Insert
    suspend fun insert(viagem: Viagem)
}