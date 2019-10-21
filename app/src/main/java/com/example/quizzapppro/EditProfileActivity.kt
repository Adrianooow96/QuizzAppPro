package com.example.quizzapppro

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil
import kotlinx.android.synthetic.main.listelement.*

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

        nombreEditText = findViewById(R.id.result_edit_text_e)
        iconsRadioGroup = findViewById(R.id.icons_radio_group_e)
        opcion0RadioButton = findViewById(R.id.ginjirotchi_radio_button_e)
        opcion1RadioButton = findViewById(R.id.hashizotchi_radio_button_e)
        opcion2RadioButton = findViewById(R.id.kuchipatchi_radio_button_e)
        opcion3RadioButton = findViewById(R.id.mametchi_radio_button_e)
        opcion4RadioButton = findViewById(R.id.mimitchi_radio_button_e)
        opcion5RadioButton = findViewById(R.id.pochitchi_radio_button_e)
        confirmarButton = findViewById(R.id.btn_confirmar_e)

        var perfilactual: Perfil = db.perfilDao().getCurrentPerfil()

        nombreEditText.setText(perfilactual.nombreJugador)
        iconsRadioGroup.check(iconsRadioGroup.getChildAt(perfilactual.avatar).id)

        confirmarButton.setOnClickListener {
            //validar datos
            namesList = db.perfilDao().getAllNames()
            var name = nombreEditText.text.toString()
            var rbId = iconsRadioGroup.getCheckedRadioButtonId()
            var icon = iconsRadioGroup.indexOfChild(findViewById(rbId))

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
