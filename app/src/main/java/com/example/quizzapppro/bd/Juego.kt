package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "juego", indices = [Index(value = ["idPregunta"], unique = true)])
data class Juego (
    @PrimaryKey @ColumnInfo(name = "idPregunta") val idPregunta: Int,
    @field:ColumnInfo(name = "respondida") var respondida: Int,
    @field:ColumnInfo(name = "respuesta") var respuesta: String,
    @field:ColumnInfo(name = "esActual") var esActual: Int,
    @field:ColumnInfo(name = "usoPistas") var usoPistas: Int
)