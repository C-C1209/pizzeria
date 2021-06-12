package com.example.pizzeria.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzeria.R
import com.example.pizzeria.modals.Bebida

class AdapterBebida (val bebidas:ArrayList <Bebida> ): RecyclerView.Adapter<AdapterBebida.ViewHolder> () {
 var position = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBebida.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_bebida,parent , false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return bebidas.size
    }

    override fun onBindViewHolder(holder: AdapterBebida.ViewHolder, position: Int) {
        this.position = position
        holder.bindItems(bebidas[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {

        }
        fun bindItems(bebida: Bebida){
            val txtPizza: TextView = itemView.findViewById(R.id.item_bebida)
            val txtPrecio: TextView = itemView.findViewById(R.id.item_precio)
            val txtDescripcion: TextView = itemView.findViewById(R.id.item_descripcion)

            txtPizza.text = bebida.nombre
            txtPrecio.text = txtPrecio.text.toString()+""+bebida.precio.toString()
            txtDescripcion.text = bebida.descripcion
        }
    }
}