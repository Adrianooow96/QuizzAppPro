package com.example.quizzapppro


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfilesActivity : AppCompatActivity() {

    private lateinit var btnCreateProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        btnCreateProfile = findViewById(R.id.createProfile_button)

        btnCreateProfile.setOnClickListener {

            /*
            val intent = Intent(this, NewProfileActivity::class.java)
            startActivity(intent)
             */

        }
    }
}
