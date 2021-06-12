package com.example.pizzeria.ui.gallery

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
import com.example.pizzeria.adapters.AdapterBebida
import com.example.pizzeria.modals.Bebida
import com.example.pizzeria.conexion.Conexion

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = view.findViewById(R.id.text_gallery)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        var recyclerView= view.findViewById<RecyclerView>(R.id.rbebida)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        var bebidas = ArrayList<Bebida>()

        var conexion = Conexion(view.context)
        var db = conexion.writableDatabase


        //insertar
        //db.execSQL("INSERT INTO bebidas(nombre, precio, descripcion) values('Coca-Cola', 15.99,'Rica bebida refrescante coca-cola de 250 ml con hielos.' )")
        //db.execSQL("INSERT INTO bebidas(nombre, precio, descripcion) values('Coca-Cola', 25.99,'Rica bebida refrescante coca-cola de 2 lts con hielos.' )")
        //db.execSQL("INSERT INTO bebidas(nombre, precio, descripcion) values('Coca-Cola', 35.99,'Rica bebida refrescante coca-cola de 3.3 lts con hielos.' )")

        //db.execSQL("delete from bebidas where id>3")

        var respuesta = db.rawQuery("SELECT * FROM bebidas", null)

        if(respuesta.moveToFirst()) {
            do {
                bebidas.add(Bebida(respuesta.getString(1),respuesta.getDouble(2),respuesta.getString(3)))
            } while (respuesta.moveToNext())
        }else{
        Log.e("ERROR","Sin datos")
        }

        val adapter = AdapterBebida(bebidas)
        recyclerView.adapter = adapter

        return view
    }
}