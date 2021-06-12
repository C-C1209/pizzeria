package com.example.pizzeria.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzeria.R
import com.example.pizzeria.modals.Pizza

class AdapterPizza (val pizzas:ArrayList <Pizza> ): RecyclerView.Adapter<AdapterPizza.ViewHolder> () {
 var position = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPizza.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pizza,parent , false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return pizzas.size
    }

    override fun onBindViewHolder(holder: AdapterPizza.ViewHolder, position: Int) {
        this.position = position
        holder.bindItems(pizzas[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {

        }
        fun bindItems( pizza: Pizza){
            val txtPizza: TextView = itemView.findViewById(R.id.item_bebida)
            val txtPrecio: TextView = itemView.findViewById(R.id.item_precio)
            val txtDescripcion: TextView = itemView.findViewById(R.id.item_descripcion)

            txtPizza.text = pizza.nombre
            txtPrecio.text = txtPrecio.text.toString()+""+pizza.precio.toString()
            txtDescripcion.text = pizza.descripcion
        }
    }
}