package com.example.roomejercicio.main.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.roomejercicio.main.model.Datos
import com.example.roomejercicio.main.model.DatosDao

class DatosRepository(private val datosDao: DatosDao) {

    val allDatos: LiveData<List<Datos>> = datosDao.getAllDatos()

    val precioTotal: LiveData<List<Datos>> = datosDao.getAllDatos()

    @WorkerThread
    suspend fun insert(datos: Datos) {
        datosDao.insert(datos)
    }
    @WorkerThread
    suspend fun deleteAll() {
        datosDao.deleteAll()
    }
}
