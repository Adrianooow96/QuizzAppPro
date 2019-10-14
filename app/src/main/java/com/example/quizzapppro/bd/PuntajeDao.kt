package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface PuntajeDao {
    @Query ("SELECT * FROM puntaje WHERE idPuntaje=:id")
    fun getPuntaje(id: Int) : Puntaje

}