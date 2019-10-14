package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "perfil", indices = [Index(value = ["idJugador"], unique = true)])
data class Perfil (
    @PrimaryKey @ColumnInfo(name = "idJugador") val idJugador: Int,
    @field:ColumnInfo(name = "nombreJugador") val nombreJugador: String,
    @field:ColumnInfo(name = "avatar") val avatar: Int,
    @field:ColumnInfo(name = "totalPreguntas") var totalPreguntas: Int,
    @field:ColumnInfo(name = "dificultad") var dificultad: Int,
    @field:ColumnInfo(name = "usarPistas") var usarPistas: Int,
    @field:ColumnInfo(name = "numeroPistas") var numeroPistas: Int,
    @field:ColumnInfo(name = "categoriasElegidas") var categoriasElegidas: String
)