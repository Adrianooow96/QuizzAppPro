package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface Dificultad_CatalogoDao {
    @Query ("SELECT * FROM dificultad_catalogo WHERE idDificultad=:id")
    fun getDificultadCatalogo(id: Int) : Dificultad_Catalogo

}