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

    @Query ("SELECT * FROM puntaje WHERE idPuntaje =(SELECT MAX (idPuntaje) FROM puntaje)")
    fun getLastPuntaje() : Puntaje

    @Query("SELECT * FROM puntaje ORDER BY puntaje DESC")
    fun getAllOrdered(): List<Puntaje>

    @Query("SELECT * FROM puntaje WHERE idPuntaje=:id")
    fun getPuntajeById(id: Int): Puntaje

    @Query("SELECT * FROM puntaje WHERE perfil_idJugador=:id")
    fun getPuntajeByPErfilId(id: Int): Puntaje

    @Query("SELECT max(puntaje) FROM puntaje")
    fun getMaxPuntaje(): Int

    @Query("INSERT INTO puntaje(puntaje, perfil_idJugador, rendimiento, cheated) VALUES(:puntaje, :idJug, :rendimiento, :cheated)")
    fun setPuntaje(puntaje: Int, idJug: Int, rendimiento : Int, cheated: Int )
}