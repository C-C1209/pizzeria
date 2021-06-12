package com.example.pizzeria.conexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Conexion (var context: Context): SQLiteOpenHelper(context, "pizzeria",null,2 ){
    var tablaPizzas= "CREATE TABLE pizzas(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " nombre varchar(30), precio double(6), descripcion varchar(150))"
    var tablaBebidas= "CREATE TABLE bebidas(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " nombre varchar(30), precio double(6), descripcion varchar(150))"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(tablaBebidas)
        db?.execSQL(tablaPizzas)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}