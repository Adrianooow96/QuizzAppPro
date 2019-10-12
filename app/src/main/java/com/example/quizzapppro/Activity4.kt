package com.example.quizzapppro

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_4.*

class Activity4 : AppCompatActivity() {

    private lateinit var tv_lastScore : TextView
    private lateinit var tv_best1 : TextView
    private lateinit var tv_best2 : TextView
    private lateinit var tv_best3 : TextView
    private lateinit var tv_best4 : TextView
    private lateinit var tv_best5 : TextView

    private lateinit var best1String : String
    private lateinit var best2String : String
    private lateinit var best3String : String
    private lateinit var best4String : String
    private lateinit var best5String : String

    private var score : Int = 0
    private var performance : Int = 0

    //private lateinit var name : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)
        val sharedPreference:SharedPreference=SharedPreference(this)
        var  intent = intent

        val bundle = intent.extras
        if (bundle != null) {
            // get your data here

            score = intent.getIntExtra(NUMBER_OF_SCORE,9)
            performance = intent.getIntExtra(NUMBER_OF_PERFORMANCE,9)
            //name = intent.getStringExtra(NAME_OF_PLAYER)

        }
        tv_lastScore = findViewById(R.id.tv_score)
        tv_best1 = findViewById(R.id.tv_best1)
        tv_best2 = findViewById(R.id.tv_best2)
        tv_best3 = findViewById(R.id.tv_best3)
        tv_best4 = findViewById(R.id.tv_best4)
        tv_best5 = findViewById(R.id.tv_best5)

        var best1 = sharedPreference.getValueInt("best1")
        var best2 = sharedPreference.getValueInt("best2")
        var best3 = sharedPreference.getValueInt("best3")
        var best4 = sharedPreference.getValueInt("best4")
        var best5 = sharedPreference.getValueInt("best5")

        //sharedPreference.save("name", name)





        if(score >= best1){
            var temp : Int = best1
            var temp4 : Int = best3
            var temp2 : Int = best4
            var temp3 : Int = best2
            best1 = score
            best2 = temp
            best3 = temp3
            best4 = temp4
            best5 = temp2


            sharedPreference.save("best5", best5)
            sharedPreference.save("best4", best4)
            sharedPreference.save("best3", best3)
            sharedPreference.save("best2", best2)
            sharedPreference.save("best1", best1)

        }
        if(score >= best2 && score < best1){
            var temp : Int = best3
            var temp2 : Int = best4
            var temp3 : Int = best2
            best2 = score
            best3= temp3
            best4 = temp
            best5 = temp2

            sharedPreference.save("best5", best5)
            sharedPreference.save("best4", best4)
            sharedPreference.save("best3", best3)
            sharedPreference.save("best2", best2)
        }
        if(score >= best3 && score < best2){
            var temp : Int = best3
            var temp2 : Int = best4
            best3 = score
            best4 = temp
            best5 = temp2

            sharedPreference.save("best5", best5)
            sharedPreference.save("best4", best4)
            sharedPreference.save("best3", best3)
        }
        if(score >= best4 && score < best3){
            var temp : Int = best4
            best4 = score
            best5 = temp
            sharedPreference.save("best5", best5)
            sharedPreference.save("best4", best4)
        }
        if(score >= best5 && score < best4){
            best5=score
            sharedPreference.save("best5", best5)
        }


        tv_lastScore.setText("LAST SCORE : " + score)

        tv_best1.setText("name1  " + sharedPreference.getValueInt("best1"))
        tv_best2.setText("name2  " + sharedPreference.getValueInt("best2"))
        tv_best3.setText("name3  " + sharedPreference.getValueInt("best3"))
        tv_best4.setText("name4  " + sharedPreference.getValueInt("best4"))
        tv_best5.setText("name5  " + sharedPreference.getValueInt("best5"))
    }
}
