package com.example.quizzapppro

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Activity5 : AppCompatActivity() {

    private lateinit var tv_best1 : TextView
    private lateinit var tv_best2 : TextView
    private lateinit var tv_best3 : TextView
    private lateinit var tv_best4 : TextView
    private lateinit var tv_best5 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
        val sharedPreference:SharedPreference=SharedPreference(this)

        tv_best1 = findViewById(R.id.tv_best1)
        tv_best2 = findViewById(R.id.tv_best2)
        tv_best3 = findViewById(R.id.tv_best3)
        tv_best4 = findViewById(R.id.tv_best4)
        tv_best5 = findViewById(R.id.tv_best5)

        tv_best1.setText("name1  " + sharedPreference.getValueInt("best1"))
        tv_best2.setText("name2  " + sharedPreference.getValueInt("best2"))
        tv_best3.setText("name3  " + sharedPreference.getValueInt("best3"))
        tv_best4.setText("name4  " + sharedPreference.getValueInt("best4"))
        tv_best5.setText("name5  " + sharedPreference.getValueInt("best5"))
    }
}
