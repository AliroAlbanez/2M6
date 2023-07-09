package com.example.roomejercicio.main.view
import DatosListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.RoomEjercicio.R
import com.example.roomejercicio.main.model.Datos


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val ids = arguments?.getString("ids")
        val nombres = arguments?.getString("nombres")
        val precios = arguments?.getDouble("precios")
        val cantidades = arguments?.getInt("cantidades")

        val datosList = mutableListOf<Datos>()

        if (ids != null && nombres != null && precios != null && cantidades != null) {
            val datos = Datos(0, nombres, precios, cantidades)
            datosList.add(datos)
        }

        val recyclerView: RecyclerView? = view?.findViewById(R.id.recyclerView)
        val adapter = DatosListAdapter(datosList)
        recyclerView?.adapter = adapter

        return inflater.inflate(R.layout.fragment_lista, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
            ListaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}