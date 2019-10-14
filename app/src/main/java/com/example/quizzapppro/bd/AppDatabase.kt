package com.example.quizzapppro.bd

import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        pregunta::class//, Course .class,
        //ProfessorCategory.class, Professor .class
    ], version = 1
)

abstract class AppDatabase : RoomDatabase() {


    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java,
                    "QuizzDB.db"
                )
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            initializeData(db)
                        }
                    })
                    .build()
            }

            return INSTANCE as AppDatabase
        }

        fun initializeData(db: SupportSQLiteDatabase) {
            //db.beginTransaction();

            db.execSQL("INSERT INTO pregunta (idPregunta, pregunta, categoria, respuestaCorrecta, opcion1, opcion2, opcion3, respondida, respuesta, esCorrecta, usoPista) VALUES (0, 'Español', 100, 120)")

        }
    }

}