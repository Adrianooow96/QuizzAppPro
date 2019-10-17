package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface JuegoDao {
    @Query ("SELECT * FROM juego WHERE idPregunta=:id")
    fun getJuego(id: Int) : Juego

}