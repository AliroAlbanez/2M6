package com.example.roomejercicio.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.RoomEjercicio.R

class BlankFragment : Fragment() {

    interface CarroButtonClickListener {
        fun onCarroButtonClick()
        fun insertar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        //accion del boton carro que usa la interfaz CarroButtonClickListener   y ejecuta onCarroButtonClick que e
        //a su vez esta en la main activity
        view.findViewById<Button>(R.id.btnCarro).setOnClickListener {
            val listener = activity as? CarroButtonClickListener

            listener?.onCarroButtonClick()
        }
        //Accion para insertar
        view.findViewById<Button>(R.id.btnAgregar).setOnClickListener {
            val listener = activity as? CarroButtonClickListener

            listener?.insertar()
        }


        return view
    }
}
