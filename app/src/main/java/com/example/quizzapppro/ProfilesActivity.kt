package com.example.quizzapppro


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil

class ProfilesActivity : AppCompatActivity() {

    private lateinit var btnCreateProfile: Button
    private lateinit var lvProfiles: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        val db = AppDatabase.getAppDatabase(this)
        //val perfil: Perfil = db.perfilDao().getCurrentPerfil()

        btnCreateProfile = findViewById(R.id.createProfile_button)
        lvProfiles = findViewById(R.id.list_view_profiles)

        val listItems = listOf<Perfil>(Perfil(0, "AGC", 1, 5,"media", 0, "Español", 1),
            Perfil(1, "MCS", 1, 5,"media", 0, "Español", 1),
            Perfil(2, "MCM", 1, 5,"media", 0, "Español", 1))



        btnCreateProfile.setOnClickListener {

            /*
            val intent = Intent(this, NewProfileActivity::class.java)
            startActivity(intent)
             */

        }
    }
}
