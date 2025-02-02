package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "perfil")
data class Perfil (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idJugador") val idJugador: Int,
    @field:ColumnInfo(name = "nombreJugador") var nombreJugador: String,
    @field:ColumnInfo(name = "avatar") var avatar: Int,
    @field:ColumnInfo(name = "totalPreguntas") var totalPreguntas: Int,
    @field:ColumnInfo(name = "dificultad") var dificultad: Int,
    @field:ColumnInfo(name = "numeroPistas") var numeroPistas: Int,
    @field:ColumnInfo(name = "categoriasElegidas") var categoriasElegidas: Int,
    @field:ColumnInfo(name = "status") var status: Int
)