package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface PreguntaDao {
    @Query ("SELECT * FROM pregunta WHERE idPregunta=:id")
    fun getPregunta(id: Int) : pregunta


    @Query("SELECT * FROM pregunta")
    fun getAll(): List<pregunta>

}