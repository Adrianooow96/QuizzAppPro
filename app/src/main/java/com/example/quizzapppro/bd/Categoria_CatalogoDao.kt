package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface Categoria_CatalogoDao {
    @Query ("SELECT * FROM categoria_catalogo WHERE idCategoria=:id")
    fun getCategoriaCatalogo(id: Int) : Categoria_catalogo

}