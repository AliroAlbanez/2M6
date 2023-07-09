package com.example.roomejercicio.main.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Datos class
@Database(entities = arrayOf(Datos::class), version = 1, exportSchema = false)
public abstract class DatosRoomDatabase : RoomDatabase() {

    abstract fun datosDao(): DatosDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DatosRoomDatabase? = null

        fun getDatabase(context: Context): DatosRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatosRoomDatabase::class.java,
                    "datos_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
