package com.example.quizzapppro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Puntaje
import com.example.quizzapppro.bd.customScoreAdapter
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import com.muddzdev.styleabletoast.StyleableToast


class FinishScoreActivity : AppCompatActivity() {

    private lateinit var lvScores: ListView
    private lateinit var customAdapter: customScoreAdapter
    private lateinit var tvScore: TextView
    private lateinit var scoresArrayList : ArrayList<Puntaje>
    private var backPressedTime: Long = 0
    private lateinit var rendimientoImg : ImageView
    val db = AppDatabase.getAppDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_score)

        var puntaje = db.puntajeDao().getLastPuntaje()
        var rendimiento = puntaje.rendimiento

        lvScores = findViewById(R.id.lv_scores)
        tvScore = findViewById(R.id.actual_score)
        tvScore.text = puntaje.puntaje.toString()
        rendimientoImg = findViewById(R.id.rendimiento_img)

        scoresArrayList = db.puntajeDao().getAllOrdered() as ArrayList<Puntaje>
        if(scoresArrayList.size != 0){
            customAdapter = customScoreAdapter(this, scoresArrayList)
            lvScores.adapter = customAdapter
        }

        when{
            rendimiento <= 50 -> rendimientoImg.setImageResource(R.drawable.stupid)
            rendimiento in 51..69 -> rendimientoImg.setImageResource(R.drawable.meh)
            rendimiento in 70..99 -> rendimientoImg.setImageResource(R.drawable.flame)
            rendimiento == 100 -> rendimientoImg.setImageResource(R.drawable.trophy)
        }

    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("EXIT", true)
            db.juegoDao().deleteAll()
            startActivity(intent)
        } else {
            StyleableToast.makeText(this, "Presiona de nuevo para salir", Toast.LENGTH_SHORT, R.style.exitToast).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}
