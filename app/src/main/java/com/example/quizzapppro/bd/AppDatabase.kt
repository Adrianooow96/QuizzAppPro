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

            db.execSQL("INSERT INTO categoria_catalogo VALUES (0, 'Español')")
            db.execSQL("INSERT INTO categoria_catalogo VALUES (1, 'Matemáticas')")
            db.execSQL("INSERT INTO categoria_catalogo VALUES (2, 'Ciencias')")
            db.execSQL("INSERT INTO categoria_catalogo VALUES (3, 'Geografia')")
            db.execSQL("INSERT INTO categoria_catalogo VALUES (4, 'Historia')")
            db.execSQL("INSERT INTO categoria_catalogo VALUES (5, 'Cívica')")

            db.execSQL("INSERT INTO dificultad_catalogo VALUES (0, 'Fácil')")
            db.execSQL("INSERT INTO dificultad_catalogo VALUES (1, 'Medio')")
            db.execSQL("INSERT INTO dificultad_catalogo VALUES (2, 'Difícil')")

            db.execSQL("INSERT INTO pregunta VALUES (0, 'Para organizar los acontecimientos históricos de un relato podemos utilizar:', 0, 'Palabras o frases que indiquen tiempo', 'Sustantivos', 'Verbos conjugados en futuro', 'Oraciones tópico', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (1, 'Son dichos agudos y sentenciosos, de uso popular:', 0, 'Refranes', 'Chistes', 'Fábulas', 'Poemas', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (2, 'Es una característica de las fábulas:', 0, 'Señalan los defectos humanos', 'Explicar el origen del mundo', 'Finalizar con una pregunta', 'Utilizar personajes fantasmagóricos', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (3, 'La moraleja es:', 0, 'La enseñanza o lección que se deduce de una fábula', 'La opinión acerca de cómo está escrita la fábula', 'La forma en la que debemos actuar siempre', 'Lo que dice uno de los personajes al iniciar la fábula', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (4, 'Son frases que se utilizan para dar a conocer un producto:', 0, 'Sugestivas', 'Términos técnicos', 'Eslogan', 'Imágenes', 0, NULL, NULL, NULL)")


            db.execSQL("INSERT INTO pregunta VALUES (5, 'Es un ejemplo de fracción impropia:', 1, '10/5', '5/15', '4/7', '4 y 1/3', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (6, 'Es la cantidad sobrante de una división:', 1, 'Residuo', 'Dividendo', 'Cociente', 'Factor', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (7, '¿A cuántos mililitros es igual ½ litro?', 1, '500 mililitros', '100 mililitros', '200 mililitros', '5 000 mililitros', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (8, '¿Cuántos gramos tiene un kilogramo?', 1, '1 000 gramos', '10 000 gramos', '500 gramos', '100 gramos', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (9, '¿A qué número es equivalente el siguiente número romano: LXXVI?', 1, '76', '126', '526', '986', 0, NULL, NULL, NULL)")

            db.execSQL("INSERT INTO pregunta VALUES (10, 'No es una característica de una dieta correcta:', 2, 'Limpia', 'Equilibrada', 'Inocua', 'Variada', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (11, 'Nivel de adicción caracterizado por el uso frecuente de una droga durante un periodo extenso:', 2, 'Abuso', 'Uso experimental', 'Recuperación', 'Dependencia', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (12, 'Única forma de saber si se tiene VIH-SIDA', 2, 'Prueba de laboratorio', 'Tener contacto sexual sin protección', 'Billings', 'Implante anticonceptivo', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (13, 'No es una Infección de Transmisión Sexual:', 2, 'Sarcoma', 'Gonorrea', 'Sífilis', 'SIDA', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (14, 'Etapa de la adolescencia en la que se presentan cambios en el cuerpo y en el desarrollo de los seres humanos:', 2, 'Pubertad', 'Juventud', 'Maduración sexual', 'Ovulación', 0, NULL, NULL, NULL)")

            db.execSQL("INSERT INTO pregunta VALUES (15, 'Puerto que fue atacado por los barcos de guerra de Estados Unidos:', 3, 'Veracruz', 'Ensenada', 'Manzanillo', 'Progreso', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (16, 'Lucha armada en que se combaten entre sí los habitantes de un pueblo o nación', 3, 'Guerra civil', 'Abolición', 'Hacienda pública', 'Soberanía', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (17, 'País que le prestaba dinero a México durante el gobierno de Guadalupe Victoria', 3, 'Inglaterra', 'Francia', 'Estados Unidos', 'España', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (18, 'Perdió la batalla de San Jacinto en 1836', 3, 'Antonio López de Santa Anna', 'Guadalupe Victoria', 'Nicolás Bravo', 'Agustín de Iturbide', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (19, '¿En qué década comienza el imperio de Iturbide', 3, 'Década de los 20', 'Década de los 50', 'Década de los 40', 'Década de los 30', 0, NULL, NULL, NULL)")

            db.execSQL("INSERT INTO pregunta VALUES (20, '¿Qué continente tiene la mayor extensión territorial?', 4, 'Asia', 'África', 'América', 'Oceanía', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (21, 'Es la distancia entre un punto cualquiera y el Ecuador:', 4, 'Latitud', 'Altitud', 'Grados', 'Longitud', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (22, 'Distancia vertical de un lugar cualquiera sobre la superficie de la tierra, que toma como referencia el nivel del mar:', 4, 'Altitud', 'Latitud', 'Eje terrestre', 'Longitud', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (23, 'Ejemplo de fronteras naturales:', 4, 'Ríos', 'Placas tectónicas', 'Hemisferios', 'Murallas', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (24, 'Son las líneas imaginarias que se extienden de un polo a otro:', 4, 'Meridianos', 'Ecuador', 'Grados', 'Paralelos', 0, NULL, NULL, NULL)")

            db.execSQL("INSERT INTO pregunta VALUES (25, 'Trastorno que consiste en comer mucho y después provocarse el vómito:', 5, 'Bulimia', 'Sífilis', 'Trastorno alimentario compulsivo', 'Anorexia', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (26, 'Características atribuidas por una sociedad a un grupo de personas:', 5, 'Estereotipos', 'Resiliencia', 'Discriminación', 'Prejuicios', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (27, 'Cuando una persona muestra fortaleza ante las adversidades, se dice que es:', 5, 'Resiliente', 'Madura', 'Ruda', 'Resistente', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (28, 'Es una acción que contribuye a un proyecto de vida sano y seguro:', 5, 'Comer tranquilamente', 'Comer más rápido', 'Comer menos', 'No desayunar', 0, NULL, NULL, NULL)")
            db.execSQL("INSERT INTO pregunta VALUES (29, 'Es cuando se menosprecia o se humilla a las personas por sus características y capacidades:', 5, 'Discriminación', 'Adversidad', 'Procrastinación', 'Diversidad', 0, NULL, NULL, NULL)")

        }
    }

}