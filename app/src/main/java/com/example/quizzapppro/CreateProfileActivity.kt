package com.example.quizzapppro

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil
import com.example.quizzapppro.bd.PerfilDao
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_name.*

class CreateProfileActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_create_profile)

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
            if(namesList.contains(name)) {
                StyleableToast.makeText(this, "Alias en uso", Toast.LENGTH_SHORT, R.style.sameToast).show()
            }
            else if(iconsRadioGroup.getCheckedRadioButtonId() == -1){
                StyleableToast.makeText(this, "Seleccione un avatar", Toast.LENGTH_SHORT, R.style.avatarToast).show()
            }
            else if (TextUtils.isEmpty(name)){
                nombreEditText.error = "Escribe tu alias"
            }
            else{
                db.perfilDao().createNewPerfil(name, icon)
               var currentId = db.perfilDao().getIdByName(name)
                var selectedPerfil = db.perfilDao().getPerfilById(currentId)
                db.perfilDao().resetStatus()
                selectedPerfil.status = 1
                db.perfilDao().updatePerfil(selectedPerfil)
                finish()
            }
        }

    }
}
