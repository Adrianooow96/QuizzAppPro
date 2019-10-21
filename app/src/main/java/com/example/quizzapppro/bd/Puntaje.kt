package com.example.quizzapppro.bd

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "puntaje",
    foreignKeys = [ForeignKey(
        entity = Perfil::class,
        parentColumns = ["idJugador"],
        childColumns = ["perfil_idJugador"],
        onDelete = CASCADE
    )])
data class Puntaje (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idPuntaje") val idPuntaje: Int,
    @field:ColumnInfo(name = "puntaje") var puntaje: Int,
    @field:ColumnInfo(name = "perfil_idJugador") val perfil_idJugador: Int,
    @field:ColumnInfo(name = "rendimiento") var rendimiento: Int
)