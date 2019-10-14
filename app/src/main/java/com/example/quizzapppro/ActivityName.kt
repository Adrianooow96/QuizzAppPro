package com.example.quizzapppro

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val NAME_OF_PLAYER = "com.example.quizzapppro.NAME_OF_PLAYER"

class ActivityName : AppCompatActivity() {

    private lateinit var resultEditText : EditText
    private lateinit var endButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        val sharedPreference:SharedPreference=SharedPreference(this)

        resultEditText = findViewById(R.id.result_edit_text)
        endButton = findViewById(R.id.end_button)

        endButton.setOnClickListener{
            val strUserName = resultEditText.text.toString()
            if (TextUtils.isEmpty(strUserName)) {
                resultEditText.error = "Llenalo"
            } else {
                intent.putExtra(NAME_OF_PLAYER, resultEditText.text.toString())
                setResult(Activity.RESULT_OK, intent)
                startActivity(intent)
            }


        }

    }
}
