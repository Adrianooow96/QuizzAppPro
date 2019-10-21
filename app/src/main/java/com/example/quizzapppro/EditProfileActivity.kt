package com.example.quizzapppro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil

class EditProfileActivity : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var iconsRadioGroup: RadioGroup
    private lateinit var opcion0RadioButton: RadioButton
    private lateinit var opcion1RadioButton: RadioButton
    private lateinit var opcion2RadioButton: RadioButton
    private lateinit var opcion3RadioButton: RadioButton
    private lateinit var opcion4RadioButton: RadioButton
    private lateinit var opcion5RadioButton: RadioButton
    private lateinit var confirmarButton: Button
    private lateinit var namesList : List<String>
    private var icon : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val db = AppDatabase.getAppDatabase(this)

        nombreEditText = findViewById(R.id.result_edit_text)
        iconsRadioGroup = findViewById(R.id.icons_radio_group)
        opcion0RadioButton = findViewById(R.id.ginjirotchi_radio_button)
        opcion1RadioButton = findViewById(R.id.hashizotchi_radio_button)
        opcion2RadioButton = findViewById(R.id.kuchipatchi_radio_button)
        opcion3RadioButton = findViewById(R.id.mametchi_radio_button)
        opcion4RadioButton = findViewById(R.id.mimitchi_radio_button)
        opcion5RadioButton = findViewById(R.id.pochitchi_radio_button)
        confirmarButton = findViewById(R.id.btn_confirmar)

        confirmarButton.setOnClickListener {
            //validar datos
            namesList = db.perfilDao().getAllNames()
            var name = nombreEditText.text.toString()
            var rbId = iconsRadioGroup.getCheckedRadioButtonId()
            var icon = iconsRadioGroup.indexOfChild(findViewById(rbId))
            var perfilactual: Perfil = db.perfilDao().getCurrentPerfil()

            if(namesList.contains(name) && perfilactual.nombreJugador != name) {
                Toast.makeText(
                    this,
                    "Nombre No válido.",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if(iconsRadioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(
                    this,
                    "Por favor, seleccione un avatar.",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                perfilactual.nombreJugador = name
                perfilactual.avatar = icon
                db.perfilDao().updatePerfil(perfilactual)
                finish()
            }
        }

    }
}
