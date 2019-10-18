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

    @Query("SELECT * FROM perfil WHERE status = 1")
    fun getCurrentPerfil() : Perfil
}