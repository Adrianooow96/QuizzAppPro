package com.example.quizzapppro.bd

import androidx.room.Dao
import androidx.room.Query

@Dao
interface perfil_puntajeDao {
    @Query("SELECT puntaje FROM perfil_puntaje WHERE numeroPistas = 0 ORDER BY puntaje")
    fun getListScoresLegit(): List<Int>

    @Query("SELECT puntaje FROM perfil_puntaje WHERE numeroPistas > 0 ORDER BY puntaje")
    fun getListScoresCheats(): List<Int>
}