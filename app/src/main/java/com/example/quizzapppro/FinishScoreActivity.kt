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



class FinishScoreActivity : AppCompatActivity() {

    private lateinit var lvScores: ListView
    private lateinit var customAdapter: customScoreAdapter
    private lateinit var tvScore: TextView
    private lateinit var scoresArrayList : ArrayList<Puntaje>
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_score)


        val db = AppDatabase.getAppDatabase(this)
        var perfil = db.perfilDao().getCurrentPerfil()
        var puntaje = db.puntajeDao().getPuntajeByPErfilId(perfil.idJugador)

        lvScores = findViewById(R.id.lv_scores)
        tvScore = findViewById(R.id.actual_score)

        tvScore.setText(puntaje.puntaje.toString())

        scoresArrayList = db.puntajeDao().getAllOrdered() as ArrayList<Puntaje>
        if(scoresArrayList.size != 0){
            customAdapter = customScoreAdapter(this, scoresArrayList)
            lvScores.adapter = customAdapter
        }
    }

    companion object {
        lateinit var scoresArrayList: ArrayList<Puntaje>
    }


    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("EXIT", true)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Presiona de nuevo para salir",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}
