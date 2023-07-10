package com.example.roomejercicio.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.RoomEjercicio.R
import com.example.RoomEjercicio.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    // Interfaz para manejar los eventos de los botones del fragmento
    interface CarroButtonClickListener {
        fun onCarroButtonClick()
        fun insertar()
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del fragmento desde el archivo de diseño 'fragment_blank.xml'
        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        // Configura la acción del botón "Carro"
        view.findViewById<Button>(R.id.btnCarro).setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            val listener = activity as? CarroButtonClickListener

            // Llama al método onCarroButtonClick de la interfaz si está implementado en la actividad
            listener?.onCarroButtonClick()
        }

        // Configura la acción del botón "Agregar"
        view.findViewById<Button>(R.id.btnAgregar).setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            val listener = activity as? CarroButtonClickListener

            // Llama al método insertar de la interfaz si está implementado en la actividad
            listener?.insertar()
        }
        return view
    }
}
