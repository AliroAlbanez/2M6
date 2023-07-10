package com.example.roomejercicio.main.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatosDao {

    @Query("SELECT * FROM TABLE_DATOS ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<Datos>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(datos: Datos)
    @Query("SELECT SUM(precio) FROM TABLE_DATOS")
    fun getPrecio(): LiveData<Double>

    @Query("DELETE FROM TABLE_DATOS")
    suspend fun deleteAll()
}
