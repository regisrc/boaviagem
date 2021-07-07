package com.example.boaviagem.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val email: String,
        val name: String,
        val password: String
)