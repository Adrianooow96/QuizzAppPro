package com.example.quizzapppro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

var listaCategorias : List<String> = listOf("Espanol", "Matemáticas", "Ciencias", "Historia", "Geografia", "Cívica")
var numPreguntas = 18
var dificultad : String = "Media"
var numPistas : Int = 0

class MainActivity : AppCompatActivity() {

    private lateinit var play_button : Button
    private lateinit var options_button : Button
    private lateinit var score_button : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        play_button = findViewById(R.id.play_button)
        options_button = findViewById(R.id.options_button)
        score_button = findViewById(R.id.score_button)
/*

*/
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

    }

}
