package com.example.quizzapppro.bd

import androidx.room.*

@Entity
data class Perfil_Puntaje (
    @field:ColumnInfo(name = "id") val id: Int,
    @field:ColumnInfo(name = "idJugador") val idJugador: Int,
    @field:ColumnInfo(name = "nombreJugador") val nombreJugador: String,
    @field:ColumnInfo(name = "avatar") val avatar: Int,
    @field:ColumnInfo(name = "totalPreguntas") var totalPreguntas: Int,
    @field:ColumnInfo(name = "dificultad") var dificultad: String,
    @field:ColumnInfo(name = "numeroPistas") var numeroPistas: Int,
    @field:ColumnInfo(name = "categoriasElegidas") var categoriasElegidas: String,
    @field:ColumnInfo(name = "status") var status: Int,
    @field:ColumnInfo(name = "idPuntaje") val idPuntaje: Int,
    @field:ColumnInfo(name = "puntaje") val puntaje: Int,
    @field:ColumnInfo(name = "perfil_idJugador") val perfil_idJugador: Int,
    @field:ColumnInfo(name = "rendimiento") var rendimiento: Int
)

@Dao
interface Perfil_PuntajeDao {
    @Query("SELECT puntaje FROM perfil_puntaje WHERE numeroPistas = 0 ORDER BY puntaje")
    fun getListScoresLegit(): List<Int>
    @Query("SELECT puntaje FROM perfil_puntaje WHERE numeroPistas > 0 ORDER BY puntaje")
    fun getListScoresCheats(): List<Int>



}