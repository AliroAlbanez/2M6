import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.RoomEjercicio.R
import com.example.roomejercicio.main.model.Datos

class DatosListAdapter(private var datos: List<Datos>) :
    RecyclerView.Adapter<DatosListAdapter.DatosViewHolder>() {

    // Crea una nueva vista para cada elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {
        // Infla el layout del elemento de lista desde el archivo de diseño 'article_item.xml'
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return DatosViewHolder(view)
    }

    // Vincula los datos del elemento en la posición dada con la vista
    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {
        // Obtiene los datos actuales en la posición dada
        val currentDatos = datos[position]
        // Vincula los datos con la vista del titular de los datos
        holder.bind(currentDatos)
    }

    // Devuelve la cantidad total de elementos en la lista de datos
    override fun getItemCount(): Int = datos.size

    // Define la clase ViewHolder que representa cada elemento de la lista
    class DatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Referencias a los elementos de la vista
        private val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        private val precioTextView: TextView = view.findViewById(R.id.precioTextView)
        private val cantidadTextView: TextView = view.findViewById(R.id.cantidadTextView)

        // Vincula los datos con los elementos de la vista
        fun bind(datos: Datos) {
            // Establece los valores de los campos de texto con los datos correspondientes
            nombreTextView.text = datos.nombre
            precioTextView.text = datos.precio.toString()
            cantidadTextView.text = datos.cantidad.toString()
        }
    }
}