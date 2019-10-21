package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface JuegoDao {

    @Update
    fun updateJuego(juego: Juego)

    @Query ("DELETE FROM juego")
    fun deleteAll()

    @Query ("SELECT * FROM juego WHERE id=:id")
    fun getJuegoById(id: Int) : Juego

    @Query ("SELECT count(*) FROM juego WHERE respondida != 0")
    fun getTotalRespondidas() : Int

    @Query ("SELECT count(*) FROM juego WHERE respondida = 1")
    fun getTotalBuenas() : Int

    @Query ("SELECT count(*) FROM juego WHERE usoPistas != 0")
    fun getTotalPistas() : Int

    @Query ("INSERT INTO juego(id, idPregunta, respondida, esActual, usoPistas) VALUES(:id, :idPregunta, 0, 0, 0)")
    fun insertRow(idPregunta: Int,id: Int )

}