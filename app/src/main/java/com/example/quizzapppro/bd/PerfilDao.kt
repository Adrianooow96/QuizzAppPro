package com.example.quizzapppro.bd

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface PerfilDao {
    @Query ("SELECT * FROM perfil WHERE idJugador=:id")
    fun getPerfil(id: Int) : Perfil

}