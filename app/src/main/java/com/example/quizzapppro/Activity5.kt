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
    val perfil: Perfil = db.perfilDao().getCurrentPerfil()
    val idJugador = perfil.idJugador

    private lateinit var tv_best1 : TextView
    private lateinit var tv_best2 : TextView
    private lateinit var tv_best3 : TextView
    private lateinit var tv_best4 : TextView
    private lateinit var tv_best5 : TextView
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
        val sharedPreference:SharedPreference=SharedPreference(this)

        tv_best1 = findViewById(R.id.tv_best1)
        tv_best2 = findViewById(R.id.tv_best2)
        tv_best3 = findViewById(R.id.tv_best3)
        tv_best4 = findViewById(R.id.tv_best4)
        tv_best5 = findViewById(R.id.tv_best5)

        listView = findViewById<ListView>(R.id.list_view)

        /*
        tv_best1.setText("name1  " + sharedPreference.getValueInt("best1"))
        tv_best2.setText("name2  " + sharedPreference.getValueInt("best2"))
        tv_best3.setText("name3  " + sharedPreference.getValueInt("best3"))
        tv_best4.setText("name4  " + sharedPreference.getValueInt("best4"))
        tv_best5.setText("name5  " + sharedPreference.getValueInt("best5"))
         */
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, puntajes)
        //listView.adapter = adapter
    }
}
