package com.example.quizzapppro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase


var listaCategorias : List<String> = listOf("Espanol", "Matemáticas", "Ciencias", "Historia", "Geografia", "Cívica")
var numPreguntas = 18
var dificultad : String = "Media"
var numPistas : Int = 0

class MainActivity : AppCompatActivity() {

    private lateinit var play_button : Button
    private lateinit var options_button : Button
    private lateinit var score_button : Button
    private lateinit var profiles_button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)


        play_button = findViewById(R.id.play_button)
        options_button = findViewById(R.id.options_button)
        score_button = findViewById(R.id.score_button)
        profiles_button = findViewById(R.id.profiles_button)


        play_button.setOnClickListener(){
            val intent : Intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
        options_button.setOnClickListener{
            val intent : Intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        score_button.setOnClickListener() {
            val intent : Intent = Intent(this, Activity5::class.java)
            startActivity(intent)
        }
        profiles_button.setOnClickListener() {
            val intent : Intent = Intent(this, ProfilesActivity::class.java)
            startActivity(intent)
        }

    }

}
