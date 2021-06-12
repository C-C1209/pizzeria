package com.example.pizzeria.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzeria.R
import com.example.pizzeria.adapters.AdapterPizza
import com.example.pizzeria.conexion.Conexion
import com.example.pizzeria.modals.Pizza

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var recyclerView= root.findViewById<RecyclerView>(R.id.rbebida)
        recyclerView.layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        var pizzas= ArrayList<Pizza>()


        var conexion = Conexion(root.context)
        var db = conexion.writableDatabase

        //insertar

        //db.execSQL("INSERT INTO pizzas(nombre, precio, descripcion) VALUES('Mexicana',150.50,'Pepperoni, Queso , Chile Jalapeño y Carne Molida')")
        //db.execSQL("INSERT INTO pizzas(nombre, precio, descripcion) VALUES('Hawaiana',120.00,'Piña, Pepperoni, Jamón y Queso')")
        //db.execSQL("INSERT INTO pizzas(nombre, precio, descripcion) VALUES('New York',100.00,'Extra Pepperoni y Queso')")

        //borrar
        // db.execSQL("delete from pizzas where id>0")


        //consulta
        var respuesta = db.rawQuery("SELECT * FROM pizzas", null)

        /*pizzas.add(Pizza("Hawaiana",120.50, "Pinia, Pepperoni, Jamon y Queso"))
        pizzas.add(Pizza("Mexicana",150.50, "Pepperoni, Queso , Chile Jalapenio y Carne Molida"))
        pizzas.add(Pizza("New York",210.50, "Extra Pepperoni y queso"))
        pizzas.add(Pizza("Ranchera",210.50, "Carne Molida, jalapenio, Pepperoni, Tocino, Jamon y Queso"))
        pizzas.add(Pizza("Italiana",140.50, "Pepperoni, Jamon y Queso"))*/

        if(respuesta.moveToFirst()){
            do{
                pizzas.add(Pizza(respuesta.getString(1),respuesta.getDouble(2), respuesta.getString(3)))
            }while (respuesta.moveToNext())
        }else{
            Log.e("error","sin datos")
        }

        val adapter = AdapterPizza(pizzas)
        recyclerView.adapter = adapter






        return root
    }
}