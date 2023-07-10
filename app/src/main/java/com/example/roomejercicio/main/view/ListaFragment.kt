package com.example.roomejercicio.main.view

import DatosListAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.RoomEjercicio.R
import com.example.roomejercicio.main.model.Datos

class ListaFragment : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        // Obtener referencia al RecyclerView desde el layout
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        // Crear instancia del adaptador DatosListAdapter y asignarlo al RecyclerView
        // Pasamos la lista de datos obtenida desde la actividad principal (MainActivity)
        val adapter = DatosListAdapter((activity as MainActivity).data)
        recyclerView.adapter = adapter
        // Llamamos al boton vaciar y le damos una accion
        view.findViewById<Button>(R.id.vaciar).setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            if (activity is MainActivity) {
                (activity as MainActivity).eliminar()
            }
            // Llama al método eliminar() de la actividad si está implementado
        }
        return view
    }

    companion object {
        // Constantes para los argumentos
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaFragment().apply {
                // Crear argumentos y asignarlos al fragmento
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}