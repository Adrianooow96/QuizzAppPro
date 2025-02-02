package com.example.quizzapppro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil
import com.muddzdev.styleabletoast.StyleableToast


const val EXTRA_QUESTION_TEXT = "com.example.quizzapppro.EXTRA_QUESTION_TEXT"
const val NUMBER_OF_QUESTIONS = "com.example.quizzapppro.NUMBER_OF_QUESTIONS"
const val SELECT_ANY_DIFFICULTY = "com.example.quizzapppro.SELECT_ANY_DIFFICULTY"
const val SELECT_ANY_CLUE = "com.example.quizzapppro.SELECT_ANY_CLUE"
const val SCOREACTIVITY_REQUEST_CODE = 1256

class Activity2 : AppCompatActivity() {


    private lateinit var difficultySpinner: Spinner
    private lateinit var numberSpinner: Spinner
    private lateinit var allCheckBox: CheckBox
    private lateinit var spanishCheckBox: CheckBox
    private lateinit var matCheckBox: CheckBox
    private lateinit var scienceCheckBox: CheckBox
    private lateinit var historyCheckBox: CheckBox
    private lateinit var geoCheckBox: CheckBox
    private lateinit var ethicCheckBox: CheckBox
    private lateinit var selectRadioButton: RadioGroup
    private var categoriesChecked = 6
    private lateinit var checks: ArrayList<CheckBox>
    private lateinit var estatus: RadioButton
    private lateinit var enableClues: Switch
    private var intSwitch = 0
    private val clue = arrayOf("1", "2", "3")
    private var categories = 0b000000
    private lateinit var perfil : Perfil

    private val data: MutableList<Int> = mutableListOf<Int>()

    private lateinit var btnTry: Button
    private var totalpreguntas = 0



    fun loadOpciones(perfil: Perfil){


        selectRadioButton.check(selectRadioButton.get(perfil.dificultad).id)

        when (perfil.numeroPistas)
        {
            0-> enableClues.isChecked = false
            1,2,3 -> enableClues.isChecked = true
        }




        if((perfil.categoriasElegidas and 63) == 63){
            allCheckBox.isChecked = true
            ethicCheckBox.isChecked = true
            geoCheckBox.isChecked = true
            historyCheckBox.isChecked = true
            scienceCheckBox.isChecked = true
            matCheckBox.isChecked = true
            spanishCheckBox.isChecked = true

            spanishCheckBox.isEnabled = false
            matCheckBox.isEnabled = false
            geoCheckBox.isEnabled = false
            ethicCheckBox.isEnabled = false
            historyCheckBox.isEnabled = false
            scienceCheckBox.isEnabled = false
        }
        else{
            spanishCheckBox.isEnabled = true
            matCheckBox.isEnabled = true
            geoCheckBox.isEnabled = true
            ethicCheckBox.isEnabled = true
            historyCheckBox.isEnabled = true
            scienceCheckBox.isEnabled = true

            if ((perfil.categoriasElegidas and 1) == 1) {
                ethicCheckBox.isChecked = true
            }
            if ((perfil.categoriasElegidas and 2) == 2) {
                geoCheckBox.isChecked = true
            }
            if ((perfil.categoriasElegidas and 4) == 4) {
                historyCheckBox.isChecked = true
            }
            if ((perfil.categoriasElegidas and 8) == 8) {
                scienceCheckBox.isChecked = true
            }
            if ((perfil.categoriasElegidas and 16) == 16) {
                matCheckBox.isChecked = true
            }
            if ((perfil.categoriasElegidas and 32) == 32) {
                spanishCheckBox.isChecked = true
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val db = AppDatabase.getAppDatabase(this)
        perfil = db.perfilDao().getCurrentPerfil()

        enableClues = findViewById(R.id.enable_clues)

        difficultySpinner = findViewById(R.id.difficulty_spinner)

        allCheckBox = findViewById(R.id.all_checkbox)
        spanishCheckBox = findViewById(R.id.spanish_checkbox)
        matCheckBox = findViewById(R.id.mat_checkbox)
        scienceCheckBox = findViewById(R.id.science_checkbox)
        historyCheckBox = findViewById(R.id.history_checkbox)
        geoCheckBox = findViewById(R.id.geo_checkbox)
        ethicCheckBox = findViewById(R.id.ethic_checkbox)

        numberSpinner = findViewById(R.id.number_spinner)

        selectRadioButton = findViewById(R.id.select_radio_group)

        estatus = findViewById(selectRadioButton.checkedRadioButtonId)

        btnTry = findViewById(R.id.try_button)

        loadOpciones(perfil)


        for (x in 5..(categoriesChecked * 5)) {
            data.add(x)
        }


        difficultySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                 totalpreguntas = difficultySpinner.selectedItem.toString().toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        var clues: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, clue)
        clues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        numberSpinner.adapter = clues
        setResult(Activity.RESULT_CANCELED)

        var adapter: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        difficultySpinner.adapter = adapter
        setResult(Activity.RESULT_CANCELED)

        numberSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (enableClues.isChecked) {
                    when (numberSpinner.selectedItem.toString()) {
                        "1" -> intSwitch = 1
                        "2" -> intSwitch = 2
                        "3" -> intSwitch = 3
                    }

                }
                else
                {
                    intSwitch = 0
                }
            }
        }

        enableClues.setOnCheckedChangeListener { v, ischeked ->
            numberSpinner.isEnabled = ischeked
            if (enableClues.isChecked) {
                when (numberSpinner.selectedItem.toString()) {
                    "1" -> intSwitch = 1
                    "2" -> intSwitch = 2
                    "3" -> intSwitch = 3
                }
            } else {
                intSwitch = 0
            }
        }

        btnTry.setOnClickListener {
            numPistas = intSwitch
            listaCategorias = emptyList()

            perfil.numeroPistas = intSwitch


            perfil.totalPreguntas = totalpreguntas

            categories = 0
            for (x in checks){
                if(x.isChecked){
                    when(x){
                        spanishCheckBox -> categories+=0b100000
                        matCheckBox -> categories+=0b010000
                        scienceCheckBox -> categories+=0b001000
                        historyCheckBox -> categories+=0b000100
                        geoCheckBox -> categories+=0b000010
                        ethicCheckBox -> categories+=0b000001
                    }
                }
            }
            perfil.categoriasElegidas = categories

            when (estatus.text)
            {
                "Alta" -> perfil.dificultad = 0
                "Media" -> perfil.dificultad = 1
                "Baja" -> perfil.dificultad = 2
            }

            setResult(Activity.RESULT_OK, intent)
            StyleableToast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT, R.style.saveToast).show()
            db.perfilDao().updatePerfil(perfil)
        }

        checks = arrayListOf(
            spanishCheckBox,
            matCheckBox,
            geoCheckBox,
            historyCheckBox,
            scienceCheckBox,
            ethicCheckBox
        )

        allCheckBox.setOnCheckedChangeListener { _, ischecked ->
            if (allCheckBox.isChecked) {
                spanishCheckBox.isChecked = true
                matCheckBox.isChecked = true
                geoCheckBox.isChecked = true
                ethicCheckBox.isChecked = true
                historyCheckBox.isChecked = true
                scienceCheckBox.isChecked = true

                spanishCheckBox.isEnabled = false
                matCheckBox.isEnabled = false
                geoCheckBox.isEnabled = false
                ethicCheckBox.isEnabled = false
                historyCheckBox.isEnabled = false
                scienceCheckBox.isEnabled = false

            } else {
                spanishCheckBox.isEnabled = true
                matCheckBox.isEnabled = true
                geoCheckBox.isEnabled = true
                ethicCheckBox.isEnabled = true
                historyCheckBox.isEnabled = true
                scienceCheckBox.isEnabled = true
            }
        }
        difficultySpinner.setSelection(perfil.totalPreguntas-5)

        when (perfil.numeroPistas) {
            1-> numberSpinner.setSelection(0)
            2-> numberSpinner.setSelection(1)
            3-> numberSpinner.setSelection(2)
            0 -> numberSpinner.isEnabled = false
        }
    }

    fun validateData() {
        var temp = selectRadioButton.touchables as List<RadioButton>
        for(x in temp){
            if(x.text.toString() == dificultad){
                selectRadioButton.check(x.id)
            }
        }
        /*
        if(intSwitch == 0){
            enableClues.setChecked(false)
            numberSpinner.isEnabled = false
        }
        else{
            enableClues.setChecked(true)
            numberSpinner.isEnabled = true
            numberSpinner.setSelection(numPistas-1)
        }

         */
    }

    fun onCheckesClick(view: View) {
        categoriesChecked = checks.count { it.isChecked }
        data.removeAll(data)
        for (x in 5..categoriesChecked * 5) {
            data.add(x)
        }
        difficultySpinner.setSelection(categoriesChecked*5-5, true)

        when(categoriesChecked)
        {
            0 ->  btnTry.isEnabled = false
            else -> btnTry.isEnabled = true
        }
    }

    fun onCheckedChanged(view: View) {
        estatus = findViewById(selectRadioButton.checkedRadioButtonId)
        perfil.dificultad = estatus.id

       //perfil.dificultad = dificultad

    }
}
