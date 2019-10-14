package com.example.quizzapppro.bd

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase

/*
@Database(
    entities = [
        CourseLevel::class//, Course .class,
        //ProfessorCategory.class, Professor .class
    ], version = 1
)
*/
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

            db.execSQL("INSERT INTO pregunta (id, description, payment_pct, required_credits) VALUES (0, 'Licenciatura', 100, 120)")

        }
    }

}