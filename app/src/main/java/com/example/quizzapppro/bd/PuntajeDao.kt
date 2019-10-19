package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface PuntajeDao {
    @Query("SELECT * FROM puntaje ORDER BY puntaje")
    fun getAll(): List<Puntaje>

    @Query("SELECT MAX(puntaje) FROM puntaje")
    fun getPuntaje(): List<Puntaje>
}