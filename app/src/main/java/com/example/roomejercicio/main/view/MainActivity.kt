package com.example.roomejercicio.main.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.add
import androidx.fragment.app.commit
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

    private lateinit var ids: String
    private lateinit var nombres: String
    private var precios: Double = 0.0
    private var cantidades: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = DatosRoomDatabase.getDatabase(applicationContext)
        val datosDao = database.datosDao()
        val datosRepository = DatosRepository(datosDao)
        val datosViewModelFactory = DatosViewModelFactory(datosRepository)
        datosViewModel = ViewModelProvider(this, datosViewModelFactory).get(DatosViewModel::class.java)


        // Cargamos el fragmento
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BlankFragment>(R.id.fragmentContainer)
        }
    }

    // Función para el botón carro
    override fun onCarroButtonClick() {
        // Navegar al fragmento de la lista aquí
        val listaFragment = ListaFragment()

        val bundle = Bundle()
        bundle.putString("ids", ids)
        bundle.putString("nombres", nombres)
        bundle.putDouble("precios", precios)
        bundle.putInt("cantidades", cantidades)

        listaFragment.arguments = bundle

        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, listaFragment)
            addToBackStack(null)
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
    }
}
