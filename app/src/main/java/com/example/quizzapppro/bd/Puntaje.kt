package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "puntaje", indices = [Index(value = ["idPuntaje"], unique = true)])
data class Puntaje (
    @PrimaryKey @ColumnInfo(name = "idPuntaje") val idPuntaje: Int,
    @field:ColumnInfo(name = "puntaje") val puntaje: Int,
    @field:ColumnInfo(name = "idJugador") val idJugador: Int,
    @field:ColumnInfo(name = "rendimiento") var rendimiento: Int
)