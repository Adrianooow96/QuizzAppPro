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

    @Query("SELECT idPregunta FROM pregunta WHERE categoria = '0'")
    fun getAllSpanish(): List<Int>

    @Query("SELECT idPregunta FROM pregunta WHERE categoria = '1'")
    fun getAllMat(): List<Int>

    @Query("SELECT idPregunta FROM pregunta WHERE categoria = '2'")
    fun getAllScience(): List<Int>

    @Query("SELECT idPregunta FROM pregunta WHERE categoria = '3'")
    fun getAllHistory(): List<Int>

    @Query("SELECT idPregunta FROM pregunta WHERE categoria = '4'")
    fun getAllGeo(): List<Int>

    @Query("SELECT idPregunta FROM pregunta WHERE categoria = '5'")
    fun getAllEthics(): List<Int>

}