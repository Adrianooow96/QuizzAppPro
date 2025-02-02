package com.example.quizzapppro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase
import com.facebook.stetho.Stetho
import android.R.attr.data
import android.R.id.edit
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import com.example.quizzapppro.bd.Perfil
import com.example.quizzapppro.bd.pregunta


var listaCategorias : List<String> = listOf("Espanol", "Matemáticas", "Ciencias", "Historia", "Geografia", "Cívica")
//var numPreguntas = 18
var dificultad : String = "Media"
var numPistas : Int = 0
const val REQUEST_CODE_QUIZ = 1
const val SHARED_PREFS = "sharedPrefs"
const val KEY_HIGHSCORE = "highscore"
class MainActivity : AppCompatActivity() {


    private lateinit var play_button : Button
    private lateinit var options_button : Button
    private lateinit var score_button : Button
    private lateinit var profiles_button : Button
    private lateinit var perfil: Perfil
    private var countPerfiles: Int = 0

    private lateinit var txtViewHighScore : TextView
    private var highscore : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // => Inicializa librería Stetho
        Stetho.initializeWithDefaults(this)

        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)
        val allPreguntas = db.preguntaDao().getAll()
       // val pr: pregunta = db.preguntaDao().getPregunta(1)
        perfil = db.perfilDao().getCurrentPerfil()
        countPerfiles = db.perfilDao().countPerfiles()


        play_button = findViewById(R.id.play_button)
        options_button = findViewById(R.id.options_button)
        score_button = findViewById(R.id.score_button)
        profiles_button = findViewById(R.id.profiles_button)

        txtViewHighScore = findViewById(R.id.text_view_highscore)
        loadHighscore(db.puntajeDao().getMaxPuntaje())

    if (countPerfiles == 0) {
        play_button.isEnabled = false
        options_button.isEnabled = false
    }
        play_button.setOnClickListener(){
            val intent : Intent = Intent(this, Activity3::class.java)
            hayJuego(intent, db)
        }
        options_button.setOnClickListener{
            val intent : Intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        score_button.setOnClickListener() {
            val intent : Intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
        profiles_button.setOnClickListener() {
            val intent : Intent = Intent(this, ProfilesActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        // => Obtener referencia a base de datos basada en librería Room
        val db = AppDatabase.getAppDatabase(this)
        countPerfiles = db.perfilDao().countPerfiles()
        if (countPerfiles == 0) {
            play_button.isEnabled = false
            options_button.isEnabled = false
        }
        else{
            play_button.isEnabled = true
            options_button.isEnabled = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == Activity.RESULT_OK){
                var score = data?.getIntExtra(EXTRA_SCORE, 0)
                if (score != null) {
                    if(score > highscore) {
                        updateHighscore(score)
                    }
                }
            }
        }
    }

    private fun hayJuego(intent: Intent, db:AppDatabase){
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Juego en curso")
            .setMessage("¿Desea continuar el juego en curso?")
            .setPositiveButton("Sí", DialogInterface.OnClickListener { dialog, which ->
                startActivity(intent)
            })
            .setNegativeButton("No.", DialogInterface.OnClickListener { dialog, which ->
                db.juegoDao().deleteAll()
                startActivity(intent)
            }).show()
    }

    private fun loadHighscore(highscore : Int){
        txtViewHighScore.setText("Highscore: $highscore")
    }

    private fun updateHighscore(highscoreNew: Int) {
        highscore = highscoreNew
        txtViewHighScore.setText("Highscore: $highscore")

        val prefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(KEY_HIGHSCORE, highscore)
        editor.apply()
    }

}
