package com.example.quizzapppro.bd

import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase

/*@Database(
    entities = [
        pregunta::class//, Course .class,
        //ProfessorCategory.class, Professor .class
    ], version = 1
)*/

abstract class AppDatabase : RoomDatabase() {
}