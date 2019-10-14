package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "dificultad_catalogo", indices = [Index(value = ["idDificultad"], unique = true)])
data class Dificultad_Catalogo (
    @PrimaryKey @ColumnInfo(name = "idDificultad") val idDificultad: Int,
    @field:ColumnInfo(name = "nombreDificultad") val nombreDificultad: String
)