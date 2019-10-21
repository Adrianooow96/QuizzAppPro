package com.example.quizzapppro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Puntaje
import com.example.quizzapppro.bd.customScoreAdapter

class ScoreActivity : AppCompatActivity() {
    private lateinit var lvScores: ListView
    private lateinit var customAdapter: customScoreAdapter
    private lateinit var scoresArrayList : ArrayList<Puntaje>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val db = AppDatabase.getAppDatabase(this)

        lvScores = findViewById(R.id.lv_scores)

        scoresArrayList = db.puntajeDao().getAllOrdered() as ArrayList<Puntaje>
        if(scoresArrayList.size != 0){
            customAdapter = customScoreAdapter(this, scoresArrayList)
            lvScores.adapter = customAdapter
        }
    }

    companion object {
        lateinit var scoresArrayList: ArrayList<Puntaje>
    }
}
