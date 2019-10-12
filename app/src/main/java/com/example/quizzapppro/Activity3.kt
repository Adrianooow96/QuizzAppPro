package com.example.quizzapppro

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
const val NUMBER_OF_PERFORMANCE = "com.example.quizzapppro.NUMBER_OF_PERFORMANCE"
const val NUMBER_OF_SCORE = "com.example.quizzapppro.NUMBER_OF_SCORE"

class Activity3 : AppCompatActivity() {

    private lateinit var tvPistas : TextView

    private lateinit var tvPreguntaActual : TextView

    private lateinit var btnPrimeraOpcion : Button
    private lateinit var btnSegundaOpcion : Button
    private lateinit var btnTerceraOpcion : Button
    private lateinit var btnCuartaOpcion : Button

    private lateinit var btnSiguiente : Button
    private lateinit var btnAnterior : Button
    private lateinit var btnPista : Button

    private lateinit var opciones : List<Button>

    private val model by lazy{ ViewModelProviders.of(this)[GameViewModel::class.java]}
    private fun validateQuestion(){
        if(model.getCurrentQuestion().respondida){
            if (model.getCurrentQuestion().esCorrecta == true){
                opciones.forEach{
                    if(it.text.toString() == model.getCurrentQuestion().respuestaCorrecta.toString()){
                        it.setBackgroundColor(ContextCompat.getColor(this,R.color.green))
                        it.setTextColor(ContextCompat.getColor(this, R.color.black))
                    }
                    else{
                        it.setBackgroundColor(ContextCompat.getColor(this,R.color.invalidButton))
                        it.setTextColor(ContextCompat.getColor(this, R.color.invalidButtonText))
                    }
                }
            }
            else{
                opciones.forEach{
                    if(it.text == model.getCurrentQuestion().respuesta){
                        it.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
                        it.setTextColor(ContextCompat.getColor(this, R.color.black))
                    }
                    else if(it.text == model.getCurrentQuestion().respuestaCorrecta.toString()){
                        it.setBackgroundColor(ContextCompat.getColor(this,R.color.green))
                        it.setTextColor(ContextCompat.getColor(this, R.color.black))
                    }
                    else{
                        it.setBackgroundColor(ContextCompat.getColor(this,R.color.invalidButton))
                        it.setTextColor(ContextCompat.getColor(this, R.color.invalidButtonText))
                    }
                }
            }
            btnPrimeraOpcion.isEnabled = false
            btnSegundaOpcion.isEnabled = false
            btnTerceraOpcion.isEnabled = false
            btnCuartaOpcion.isEnabled = false
        }
        else {
            btnPrimeraOpcion.isEnabled = true
            btnSegundaOpcion.isEnabled = true
            btnTerceraOpcion.isEnabled = true
            btnCuartaOpcion.isEnabled = true
            btnPrimeraOpcion.setBackgroundColor(ContextCompat.getColor(this,R.color.invalidButton))
            btnSegundaOpcion.setBackgroundColor(ContextCompat.getColor(this,R.color.invalidButton))
            btnTerceraOpcion.setBackgroundColor(ContextCompat.getColor(this,R.color.invalidButton))
            btnCuartaOpcion.setBackgroundColor(ContextCompat.getColor(this,R.color.invalidButton))
            btnPrimeraOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
            btnSegundaOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
            btnTerceraOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
            btnCuartaOpcion.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

    }

    private fun updateQuestion(){

        tvPreguntaActual.setText(model.getCurrentQuestion().id)

        if(model.getCurrentQuestion().usedHelp)
        {

            var respuestas = listOf<String>(
                model.getCurrentQuestion().respuestaCorrecta,
                model.getCurrentQuestion().opcion1,
                model.getCurrentQuestion().opcion2
            ).shuffled()

            btnPrimeraOpcion.setText(respuestas.get(0))
            btnSegundaOpcion.setText(respuestas.get(1))
            btnTerceraOpcion.setText(respuestas.get(2))
            btnCuartaOpcion.visibility = View.GONE

            btnPista.isEnabled = false
        }
        else{
            btnCuartaOpcion.visibility = View.VISIBLE

            var respuestas = listOf<String>(
                model.getCurrentQuestion().respuestaCorrecta,
                model.getCurrentQuestion().opcion1,
                model.getCurrentQuestion().opcion2,
                model.getCurrentQuestion().opcion3
            ).shuffled()

            btnPrimeraOpcion.setText(respuestas.get(0))
            btnSegundaOpcion.setText(respuestas.get(1))
            btnTerceraOpcion.setText(respuestas.get(2))
            btnCuartaOpcion.setText(respuestas.get(3))

            btnPista.isEnabled = true
        }

        validateQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        tvPistas =findViewById(R.id.pistas_usadas_text_view)
        tvPreguntaActual =findViewById(R.id.pregunta_actual_text_view)
        btnPrimeraOpcion =findViewById(R.id.first_option_button)
        btnSegundaOpcion =findViewById(R.id.second_option_button)
        btnTerceraOpcion =findViewById(R.id.third_option_button)
        btnCuartaOpcion =findViewById(R.id.fourth_option_button)

        btnSiguiente =findViewById(R.id.next_button)
        btnAnterior =findViewById(R.id.previous_button)
        btnPista =findViewById(R.id.hint_button)

        opciones = listOf<Button>(btnPrimeraOpcion, btnSegundaOpcion, btnTerceraOpcion, btnCuartaOpcion)

        model.setQuestions()

        updateQuestion()


        //Toast.makeText(this, array[0], Toast.LENGTH_SHORT).show()

        btnSiguiente.setOnClickListener {
            if(model.answeredQuestions < model.numOfQuestions){
                model.nextQuestion()
                updateQuestion()
                /*
                var string : String = sharedPreference.getValueInt("best1").toString()
                Toast.makeText(this, string, Toast.LENGTH_SHORT).show()

                 */
            }
            else{
                //INTENT A SCOREACTIVITY
                val intent = Intent(this, Activity4::class.java)
                var score: Int
                score = model.getScore()
                var performance :Int
                performance = model.getPerformance()

                intent.putExtra(NUMBER_OF_SCORE, score)
                intent.putExtra(NUMBER_OF_PERFORMANCE, performance)

                startActivity(intent)

            }
        }
        btnAnterior.setOnClickListener {
            model.prevQuestion()

            updateQuestion()
        }

        btnPista.setOnClickListener {
            model.getCurrentQuestion().usedHelp = true
            updateQuestion()
        }
    }



    fun onButtonClick(view: View){
        val button = view as Button
        model.getCurrentQuestion().respondida = true
        model.getCurrentQuestion().respuesta = button.text.toString()
        if(model.getCurrentQuestion().respuesta == model.getCurrentQuestion().respuestaCorrecta.toString()){
            model.getCurrentQuestion().esCorrecta = true
        }
        validateQuestion()
        if(model.answeredQuestions == model.numOfQuestions){
            btnSiguiente.setText("Finalizar")
        }
    }
}
