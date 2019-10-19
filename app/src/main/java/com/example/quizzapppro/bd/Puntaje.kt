package com.example.quizzapppro.bd

import androidx.room.*

@Entity(tableName = "puntaje",
    foreignKeys = [ForeignKey(
        entity = Perfil::class,
        parentColumns = ["idJugador"],
        childColumns = ["perfil_idJugador"]
    )])
data class Puntaje (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idPuntaje") val idPuntaje: Int,
    @field:ColumnInfo(name = "puntaje") val puntaje: Int,
    @field:ColumnInfo(name = "perfil_idJugador") val perfil_idJugador: Int,
    @field:ColumnInfo(name = "rendimiento") var rendimiento: Int
)