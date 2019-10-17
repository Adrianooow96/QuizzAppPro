package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pregunta", indices = [Index(value = ["idPregunta"], unique = true)])
data class pregunta (
    @PrimaryKey @ColumnInfo(name = "idPregunta") val idPregunta: Int,
    @field:ColumnInfo(name = "pregunta") val description: String,
    @field:ColumnInfo(name = "categoria") val categoria: Int,
    @field:ColumnInfo(name = "respuestaCorrecta") var respuestaCorrecta: String,
    @field:ColumnInfo(name = "opcion1") var opcion1: String,
    @field:ColumnInfo(name = "opcion2") var opcion2: String,
    @field:ColumnInfo(name = "opcion3") var opcion3: String
)