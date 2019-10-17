package com.example.quizzapppro.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "categoria_catalogo")
data class Categoria_catalogo (
    @PrimaryKey @ColumnInfo(name = "idCategoria") val idCategoria: Int,
    @field:ColumnInfo(name = "nombreCategoria") val nombreCategoria: String
)