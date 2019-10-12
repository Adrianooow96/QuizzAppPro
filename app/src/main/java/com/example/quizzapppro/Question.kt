package com.example.quizzapppro

data class Question (
    val id : Int,
    val categoria : String,
    val respuestaCorrecta : String,
    val opcion1 : String,
    val opcion2 : String,
    val opcion3 : String,
    var respondida : Boolean = false,
    var respuesta : String? = null,
    var esCorrecta : Boolean? = null,
    var usedHelp : Boolean = false
)