package com.example.quizzapppro

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil
import com.example.quizzapppro.bd.Puntaje


class Activity5 : AppCompatActivity() {

    val db = AppDatabase.getAppDatabase(this)
    /*
    val perfil: Perfil = db.perfilDao().getCurrentPerfil()
    val idJugador = perfil.idJugador
     */

   // private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
      //  val sharedPreference:SharedPreference=SharedPreference(this)


       // listView = findViewById<ListView>(R.id.list_view)


        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, puntajes)
        //listView.adapter = adapter
    }
}
