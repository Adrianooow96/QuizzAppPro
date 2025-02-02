package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface PerfilDao {
    @Delete
    fun deletePerfil(perfil: Perfil)

    @Update
    fun updatePerfil(perfil : Perfil)

    @Query("SELECT * FROM perfil WHERE idJugador = :id")
    fun getPerfilById(id: Int): Perfil

    @Query("SELECT * FROM perfil ORDER BY idJugador")
    fun getAll(): List<Perfil>

    @Query("SELECT nombreJugador FROM perfil")
    fun getAllNames(): List<String>

    @Query("SELECT * FROM perfil WHERE status = 1")
    fun getCurrentPerfil() : Perfil

    @Query("SELECT COUNT(*) FROM perfil")
    fun countPerfiles() : Int

    @Query("INSERT INTO perfil(nombreJugador, avatar, totalPreguntas, dificultad, numeroPistas, categoriasElegidas, status) VALUES (:nombre, :icon, 15, 1, 0, '111111', 0)")
    fun createNewPerfil(nombre : String, icon : Int)

    @Query("UPDATE perfil SET 'status'=0")
    fun resetStatus()

    @Query("SELECT * FROM perfil WHERE nombreJugador = :nombre")
    fun getIdByName(nombre: String): Int
}