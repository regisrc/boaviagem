package com.example.boaviagem

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.boaviagem.data.model.Gasto
import com.example.boaviagem.data.model.Usuario
import com.example.boaviagem.data.model.Viagem

@Database(entities = [Usuario::class, Viagem::class, Gasto::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
public abstract class UsuarioRoomDatabase : RoomDatabase() {

   abstract fun usuarioDao(): UsuarioDao
   abstract fun viagemDao(): ViagemDao
   abstract fun gastoDao(): GastoDao

   companion object {
        @Volatile
        private var INSTANCE: UsuarioRoomDatabase? = null

        fun getDatabase(context: Context): UsuarioRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UsuarioRoomDatabase::class.java,
                        "usuario_database"
                    ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
   }
}