package com.example.quizzapppro

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.quizzapppro.bd.*
import com.muddzdev.styleabletoast.StyleableToast

const val NUMBER_OF_PERFORMANCE = "com.example.quizzapppro.NUMBER_OF_PERFORMANCE"
const val NUMBER_OF_SCORE = "com.example.quizzapppro.NUMBER_OF_SCORE"
const val EXTRA_SCORE = "com.example.quizzapppro.extraScore"

class Activity3 : AppCompatActivity() {

    val db = AppDatabase.getAppDatabase(this)
    /*
    val juego: Juego = db.juegoDao().getJuegoById(1)
    val perfil: Perfil = db.perfilDao().getCurrentPerfil()
    val idJugador = perfil.idJugador
    val puntaje: Puntaje = db.puntajeDao().getPuntajeById(idJugador)
     */

    private val model by lazy { ViewModelProviders.of(this)[GameViewModel::class.java] }
    private var preguntas: List<Int> = listOf()

    private lateinit var tvPistas: TextView

    private lateinit var tvPreguntita: TextView

    private lateinit var tvPreguntaActual: TextView

    private lateinit var btnPrimeraOpcion: Button
    private lateinit var btnSegundaOpcion: Button
    private lateinit var btnTerceraOpcion: Button
    private lateinit var btnCuartaOpcion: Button

    private lateinit var btnSiguiente: Button
    private lateinit var btnAnterior: Button
    private lateinit var btnPista: Button

    private var current: Int = 0
    private var backPressedTime: Long = 0
    private var score: Int = 0

    val perfil = db.perfilDao().getCurrentPerfil()
    private var preguntaActual = db.juegoDao().getJuegoById(current)
    private lateinit var preguntaDatos : pregunta

    private lateinit var opciones: List<Button>

    private fun prepareGame(perfil: Perfil) {


        if ((perfil.categoriasElegidas and 1) == 1) {
            preguntas += db.preguntaDao().getAllEthics()
        }
        if ((perfil.categoriasElegidas and 2) == 2) {
            preguntas += db.preguntaDao().getAllGeo()
        }
        if ((perfil.categoriasElegidas and 4) == 4) {
            preguntas += db.preguntaDao().getAllHistory()
        }
        if ((perfil.categoriasElegidas and 8) == 8) {
            preguntas += db.preguntaDao().getAllScience()
        }
        if ((perfil.categoriasElegidas and 16) == 16) {
            preguntas += db.preguntaDao().getAllMat()
        }
        if ((perfil.categoriasElegidas and 32) == 32) {
            preguntas += db.preguntaDao().getAllSpanish()
        }
        preguntas = preguntas.shuffled()
        preguntas = preguntas.subList(0, perfil.totalPreguntas)
        for (x in preguntas) {
            db.juegoDao().insertRow(x, preguntas.indexOf(x))
        }
    }

    private fun validateQuestion() {
        when(preguntaActual.respondida){
            0 ->{
                btnPrimeraOpcion.isEnabled = true
                btnSegundaOpcion.isEnabled = true
                btnTerceraOpcion.isEnabled = true
                btnCuartaOpcion.isEnabled = true
                btnPrimeraOpcion.setBackgroundColor(ContextCompat.getColor(this, R.color.invalidButton))
                btnSegundaOpcion.setBackgroundColor(ContextCompat.getColor(this, R.color.invalidButton))
                btnTerceraOpcion.setBackgroundColor(ContextCompat.getColor(this, R.color.invalidButton))
                btnCuartaOpcion.setBackgroundColor(ContextCompat.getColor(this, R.color.invalidButton))
                btnPrimeraOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
                btnSegundaOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
                btnTerceraOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
                btnCuartaOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))

            }
            1->{
                opciones.forEach {
                    if (it.text.toString() == preguntaDatos.respuestaCorrecta) {
                        it.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                        it.setTextColor(ContextCompat.getColor(this, R.color.black))
                    } else {
                        it.setBackgroundColor(ContextCompat.getColor(this, R.color.invalidButton))
                        it.setTextColor(ContextCompat.getColor(this, R.color.invalidButtonText))
                    }
                }
                btnPrimeraOpcion.isEnabled = false
                btnSegundaOpcion.isEnabled = false
                btnTerceraOpcion.isEnabled = false
                btnCuartaOpcion.isEnabled = false
            }
            2->{
                opciones.forEach {
                    if (it.text == preguntaActual.respuesta) {
                        it.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
                        it.setTextColor(ContextCompat.getColor(this, R.color.black))
                    } else if (it.text == preguntaDatos.respuestaCorrecta) {
                        it.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                        it.setTextColor(ContextCompat.getColor(this, R.color.black))
                    } else {
                        it.setBackgroundColor(ContextCompat.getColor(this, R.color.invalidButton))
                        it.setTextColor(ContextCompat.getColor(this, R.color.invalidButtonText))
                    }
                }
                btnPrimeraOpcion.isEnabled = false
                btnSegundaOpcion.isEnabled = false
                btnTerceraOpcion.isEnabled = false
                btnCuartaOpcion.isEnabled = false
            }
        }
    }

    private fun updateQuestion() {
        preguntaActual = db.juegoDao().getJuegoById(current)
        preguntaDatos = db.preguntaDao().getPregunta(preguntaActual.idPregunta)
        var pistasUsadas = db.juegoDao().getTotalPistas()

        btnPista.isEnabled = pistasUsadas < perfil.numeroPistas
        tvPistas.setText("Pistas usadas:"+pistasUsadas+"/"+perfil.numeroPistas)

        tvPreguntita.setText("Pregunta:"+(preguntaActual.id+1)+"/"+preguntas.size)

        tvPreguntaActual.setText(preguntaDatos.pregunta)

        if (preguntaActual.usoPistas == 1) {
            when (perfil.dificultad) {
                0 -> {
                    btnPrimeraOpcion.setText(preguntaDatos.respuestaCorrecta)
                    btnSegundaOpcion.visibility = View.GONE
                    btnTerceraOpcion.visibility = View.GONE
                    btnCuartaOpcion.visibility = View.GONE
                }
                1 -> {
                    var respuestas = listOf<String>(
                        preguntaDatos.respuestaCorrecta,
                        preguntaDatos.opcion1
                    ).shuffled()

                    btnPrimeraOpcion.setText(respuestas.get(0))
                    btnSegundaOpcion.setText(respuestas.get(1))
                    btnTerceraOpcion.visibility = View.GONE
                    btnCuartaOpcion.visibility = View.GONE
                }
                2 -> {
                    var respuestas = listOf<String>(
                        preguntaDatos.respuestaCorrecta,
                        preguntaDatos.opcion1,
                        preguntaDatos.opcion2
                    ).shuffled()

                    btnPrimeraOpcion.setText(respuestas.get(0))
                    btnSegundaOpcion.setText(respuestas.get(1))
                    btnTerceraOpcion.setText(respuestas.get(2))
                    btnCuartaOpcion.visibility = View.GONE
                }
            }
            btnPista.isEnabled = false
        } else {
            btnSegundaOpcion.visibility = View.VISIBLE
            btnTerceraOpcion.visibility = View.VISIBLE
            btnCuartaOpcion.visibility = View.VISIBLE

            when (perfil.dificultad) {
                2 -> {
                    var respuestas = listOf<String>(
                        preguntaDatos.respuestaCorrecta,
                        preguntaDatos.opcion1
                    ).shuffled()

                    btnPrimeraOpcion.setText(respuestas.get(0))
                    btnSegundaOpcion.setText(respuestas.get(1))
                    btnTerceraOpcion.visibility = View.GONE
                    btnCuartaOpcion.visibility = View.GONE
                }
                1 -> {
                    var respuestas = listOf<String>(
                        preguntaDatos.respuestaCorrecta,
                        preguntaDatos.opcion1,
                        preguntaDatos.opcion2
                    ).shuffled()

                    btnPrimeraOpcion.setText(respuestas.get(0))
                    btnSegundaOpcion.setText(respuestas.get(1))
                    btnTerceraOpcion.setText(respuestas.get(2))
                    btnCuartaOpcion.visibility = View.GONE
                }
                0 -> {
                    var respuestas = listOf<String>(
                        preguntaDatos.respuestaCorrecta,
                        preguntaDatos.opcion1,
                        preguntaDatos.opcion2,
                        preguntaDatos.opcion3
                    ).shuffled()

                    btnPrimeraOpcion.setText(respuestas.get(0))
                    btnSegundaOpcion.setText(respuestas.get(1))
                    btnTerceraOpcion.setText(respuestas.get(2))
                    btnCuartaOpcion.setText(respuestas.get(3))
                }
            }
        }

        validateQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val db = AppDatabase.getAppDatabase(this)
        val perfil: Perfil = db.perfilDao().getCurrentPerfil()
        current = if(db.juegoDao().getActual() >0){
            preguntas = db.juegoDao().getAll()
            db.juegoDao().getActual()
        } else{
            db.juegoDao().deleteAll()
            prepareGame(perfil)
            0
        }

        preguntaActual = db.juegoDao().getJuegoById(current)
        preguntaDatos = db.preguntaDao().getPregunta(preguntaActual.idPregunta)

        tvPreguntita =findViewById(R.id.num_pregunta_text_view)

        tvPistas = findViewById(R.id.pistas_usadas_text_view)
        tvPreguntaActual = findViewById(R.id.pregunta_actual_text_view)
        btnPrimeraOpcion = findViewById(R.id.first_option_button)
        btnSegundaOpcion = findViewById(R.id.second_option_button)
        btnTerceraOpcion = findViewById(R.id.third_option_button)
        btnCuartaOpcion = findViewById(R.id.fourth_option_button)

        btnSiguiente = findViewById(R.id.next_button)
        btnAnterior = findViewById(R.id.previous_button)
        btnPista = findViewById(R.id.hint_button)
        opciones = listOf<Button>(btnPrimeraOpcion, btnSegundaOpcion, btnTerceraOpcion, btnCuartaOpcion)

        updateQuestion()

        btnSiguiente.setOnClickListener {
            if (db.juegoDao().getTotalRespondidas() < preguntas.size) {
                current = (current + 1) % preguntas.size
                updateQuestion()
            } else {
                val intent = Intent(this, FinishScoreActivity::class.java)
                var puntaje = db.juegoDao().getTotalBuenas()*100
                var performance = puntaje/preguntas.size

                db.puntajeDao().setPuntaje(puntaje, perfil.idJugador, performance, if(perfil.numeroPistas==0){0}else{1})

                startActivity(intent)

            }
        }
        btnAnterior.setOnClickListener {
            current = (current + preguntas.size - 1) % preguntas.size
            updateQuestion()
        }

        btnPista.setOnClickListener {
            preguntaActual.usoPistas = 1
            db.juegoDao().updateJuego(preguntaActual)
            updateQuestion()
        }
    }

    private fun finishQuiz() {
        db.juegoDao().resetActual()
        preguntaActual.esActual = 1
        db.juegoDao().updateJuego(preguntaActual)
        finish()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz()
        } else {
            StyleableToast.makeText(this, "Presiona de nuevo para salir", Toast.LENGTH_SHORT, R.style.exitToast).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    fun onButtonClick(view: View) {
        val button = view as Button
        preguntaActual.respuesta = button.text.toString()
        if (preguntaActual.respuesta == preguntaDatos.respuestaCorrecta) {
            preguntaActual.respondida = 1
        }
        else{
            preguntaActual.respondida = 2
        }
        db.juegoDao().updateJuego(preguntaActual)
        validateQuestion()
        if (db.juegoDao().getTotalRespondidas() == preguntas.size) {
            btnSiguiente.setText("Finalizar")
        }
    }
}
