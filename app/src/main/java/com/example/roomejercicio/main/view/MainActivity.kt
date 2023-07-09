package com.example.roomejercicio.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.RoomEjercicio.R
import com.example.RoomEjercicio.databinding.ActivityMainBinding
import com.example.roomejercicio.main.model.Datos
import com.example.roomejercicio.main.model.DatosRoomDatabase
import com.example.roomejercicio.main.model.repository.DatosRepository
import com.example.roomejercicio.main.view.BlankFragment
import com.example.roomejercicio.main.view.ListaFragment
import com.example.roomejercicio.main.view.viewmodel.DatosViewModel
import com.example.roomejercicio.main.view.viewmodel.DatosViewModelFactory

class MainActivity : AppCompatActivity(), BlankFragment.CarroButtonClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var datosViewModel: DatosViewModel
    internal lateinit var data: List<Datos>

    private var ids: Int = 0
    private lateinit var nombres: String
    private var precios: Double = 0.0
    private var cantidades: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciar la bd
        val database = DatosRoomDatabase.getDatabase(applicationContext)
        val datosDao = database.datosDao()
        val datosRepository = DatosRepository(datosDao)
        val datosViewModelFactory = DatosViewModelFactory(datosRepository)
        datosViewModel =
            ViewModelProvider(this, datosViewModelFactory).get(DatosViewModel::class.java)

        datosViewModel.allDatos.observe(this, Observer { datosList ->
            // Puedes iterar sobre la lista y acceder a cada elemento individualmente
            for (data in datosList) {
                this.data =
                    datosList //aqui cargamos la variable global data con la lista de allDatos
                ids = data.id!!
                nombres = data.nombre
                precios = data.precio
                cantidades = data.cantidad
                //probar si llegan los datos
                Log.i(
                    "valor",
                    "ID: ${ids}," +
                         "nombre: ${nombres}, " +
                         "Precio: ${precios}," +
                         "Cantidad: ${cantidades}"
                )

            }
        })

        // Cargamos el fragmento
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BlankFragment>(R.id.fragmentContainer)
        }
    }

    // Función para el botón carro
    override fun onCarroButtonClick() {
        // Verificar si la propiedad ids está inicializada
        if (!data.isEmpty()) {
            // Acceder a la propiedad ids
            val listaFragment = ListaFragment()

            val bundle = Bundle()
            bundle.putString("ids", ids.toString())
            bundle.putString("nombres", nombres!!)
            bundle.putDouble("precios", precios!!)
            bundle.putInt("cantidades", cantidades!!)

            listaFragment.arguments = bundle

            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, listaFragment)
                addToBackStack(null)
            }
        } else {
            // La propiedad ids no ha sido inicializada
            // Realiza la lógica correspondiente en este caso
            Toast.makeText(this, "la lista esta vacia", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para el botón insertar
    override fun insertar() {
        val nombreEditText = findViewById<EditText>(R.id.nombre)
        val cantidadEditText = findViewById<EditText>(R.id.cantidad)
        val precioEditText = findViewById<EditText>(R.id.precio)

        val nombre = nombreEditText.text.toString()
        val cantidad = cantidadEditText.text.toString().toIntOrNull() ?: 0
        val precio = precioEditText.text.toString().toDoubleOrNull() ?: 0.0
        val datos = Datos(null, nombre, precio, cantidad)
        datosViewModel.insert(datos)
        Toast.makeText(this, "agregado correctamente", Toast.LENGTH_SHORT).show()
    }
    fun eliminar()
    {
        datosViewModel.deleteAll()
        data = emptyList()
        val listaFragment = ListaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, listaFragment)
            .commit()
    }


}
