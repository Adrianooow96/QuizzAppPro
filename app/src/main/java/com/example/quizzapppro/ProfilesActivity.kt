package com.example.quizzapppro


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil

class ProfilesActivity : AppCompatActivity() {

    private lateinit var btnCreateProfile: Button
    private lateinit var lvProfiles: ListView
    private lateinit var customAdapter: CustomAdapter
    private lateinit var profilesArrayList: ArrayList<Perfil>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        val db = AppDatabase.getAppDatabase(this)
        //val perfil: Perfil = db.perfilDao().getCurrentPerfil()

        btnCreateProfile = findViewById(R.id.createProfile_button)
        lvProfiles = findViewById(R.id.list_view_profiles)

        profilesArrayList = db.perfilDao().getAll() as ArrayList<Perfil>

        customAdapter = CustomAdapter(this, profilesArrayList)
        lvProfiles.adapter = customAdapter

        lvProfiles.setOnItemClickListener { _, _, position, _ ->
            val selectedPerfil = profilesArrayList[position]
            selectedPerfil.status = 1
            db.perfilDao().resetStatus()
            db.perfilDao().updatePerfil(selectedPerfil)
        }

        btnCreateProfile.setOnClickListener {
            val intent = Intent(this, CreateProfileActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        lateinit var profilesArrayList: ArrayList<Perfil>
    }

    override fun onResume() {
        super.onResume()
        Log.d("a","resumed")
        val db = AppDatabase.getAppDatabase(this)

        profilesArrayList = db.perfilDao().getAll() as ArrayList<Perfil>

        customAdapter = CustomAdapter(this, profilesArrayList)
        lvProfiles.adapter = customAdapter
    }
}
