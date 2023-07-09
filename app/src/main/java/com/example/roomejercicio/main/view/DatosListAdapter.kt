import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.RoomEjercicio.R
import com.example.roomejercicio.main.model.Datos

class DatosListAdapter(private var datos: List<Datos>) :
    RecyclerView.Adapter<DatosListAdapter.DatosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return DatosViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {
        val currentDatos = datos[position]
        holder.bind(currentDatos)
    }

    override fun getItemCount(): Int = datos.size

    class DatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        private val precioTextView: TextView = view.findViewById(R.id.precioTextView)
        private val cantidadTextView: TextView = view.findViewById(R.id.cantidadTextView)

        fun bind(datos: Datos) {
            nombreTextView.text = datos.nombre
            precioTextView.text = datos.precio.toString()
            cantidadTextView.text = datos.cantidad.toString()

        }
    }
}
