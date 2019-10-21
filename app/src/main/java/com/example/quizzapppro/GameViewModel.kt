package com.example.quizzapppro

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var questions: List<Question> = listOf()
    private var allQuestions: List<Question>
    public var currentQuestion: Int = 0
    private var score : Int = 0
    private var performance : Int = 0

    init {
        allQuestions = listOf(
            Question(
                R.string.categoria_1_question_1,
                "Espanol",
                "Palabras o frases que indiquen tiempo",
                "Sustantivos",
                "Oraciones tópico",
                "Verbos conjugados en futuro"
            ),
            Question(
                R.string.categoria_1_question_2,
                "Espanol",
                "Refranes",
                "Poemas",
                "Fábulas",
                "Chistes"
            ),
            Question(
                R.string.categoria_1_question_3,
                "Espanol",
                "Señalar los defectos humanos",
                "Utilizar personajes fantasmagóricos",
                "Finalizar con una pregunta",
                "Explicar el origen del mundo"
            ),
            Question(
                R.string.categoria_1_question_4,
                "Espanol",
                "La enseñanza o lección que se deduce de una fábula",
                "Lo que dice uno de los personajes al iniciar la fábula",
                "La forma en la que debemos actuar siempre",
                "La opinión acerca de cómo está escrita la fábula"
            ),
            Question(
                R.string.categoria_1_question_5,
                "Espanol",
                "Sugestivas",
                "Imágenes",
                "Eslogan",
                "Términos técnicos"
            ),

            Question(
                R.string.categoria_2_question_1,
                "Matemáticas",
                "10/5",
                "4 y 1/3",
                "4/7",
                "5/15"
            ),
            Question(
                R.string.categoria_2_question_2,
                "Matemáticas",
                "Residuo",
                "Factor",
                "Cociente",
                "Dividendo"
            ),
            Question(
                R.string.categoria_2_question_3,
                "Matemáticas",
                "500 mililitros",
                "5,000 mililitros",
                "200 mililitros",
                "100 mililitros"
            ),
            Question(
                R.string.categoria_2_question_4,
                "Matemáticas",
                "1,000 gramos",
                "500 gramos",
                "10,000 gramos",
                "100 gramos"
            ),
            Question(
                R.string.categoria_2_question_5,
                "Matemáticas",
                "76",
                "986",
                "526",
                "126"
            ),

            Question(
                R.string.categoria_3_question_1,
                "Ciencias",
                "Limpia",
                "Variada",
                "Inocua",
                "Equilibrada"
            ),
            Question(
                R.string.categoria_3_question_2,
                "Ciencias",
                "Abuso",
                "Dependencia",
                "Recuperación",
                "Uso experimental"
            ),
            Question(
                R.string.categoria_3_question_3,
                "Ciencias",
                "Prueba de laboratorio",
                "Implante anticonceptivo",
                "Billings",
                "Tener contacto sexual sin protección"
            ),
            Question(
                R.string.categoria_3_question_4,
                "Ciencias",
                "Sarcoma",
                "SIDA",
                "Sífilis",
                "Gonorrea"
            ),
            Question(
                R.string.categoria_3_question_5,
                "Ciencias",
                "Pubertad",
                "Ovulación",
                "Maduración sexual",
                "Juventud"
            ),

            Question(
                R.string.categoria_4_question_1,
                "Historia",
                "Veracruz",
                "Progreso",
                "Manzanillo",
                "Ensenada"
            ),
            Question(
                R.string.categoria_4_question_2,
                "Historia",
                "Guerra civil",
                "Soberanía",
                "Hacienda pública",
                "Abolición"
            ),
            Question(
                R.string.categoria_4_question_3,
                "Historia",
                "Inglaterra",
                "España",
                "Estados Unidos",
                "Francia"
            ),
            Question(
                R.string.categoria_4_question_4,
                "Historia",
                "Antonio López de Santa Anna",
                "Agustín de Iturbide",
                "Nicolás Bravo",
                "Guadalupe Victoria"
            ),
            Question(
                R.string.categoria_4_question_5,
                "Historia",
                "Década de los 20",
                "Década de los 30",
                "Década de los 40",
                "Década de los 50"
            ),

            Question(
                R.string.categoria_5_question_1,
                "Geografía",
                "Asia",
                "Oceanía",
                "América",
                "África"
            ),
            Question(
                R.string.categoria_5_question_2,
                "Geografía",
                "Latitud",
                "Longitud",
                "Grados",
                "Altitud"
            ),
            Question(
                R.string.categoria_5_question_3,
                "Geografía",
                "Altitud",
                "Longitud",
                "Eje terrestre",
                "Latitud"
            ),
            Question(
                R.string.categoria_5_question_4,
                "Geografía",
                "Ríos",
                "Murallas",
                "Hemisferios",
                "Placas tectónicas"
            ),
            Question(
                R.string.categoria_5_question_5,
                "Geografía",
                "Meridianos",
                "Paralelos",
                "Grados",
                "Ecuador"
            ),

            Question(
                R.string.categoria_6_question_1,
                "Cívica",
                "Bulimia",
                "Anorexia",
                "Trastorno alimentario compulsivo",
                "Sífilis"
            ),
            Question(
                R.string.categoria_4_question_2,
                "Cívica",
                "Estereotipos",
                "Prejuicios",
                "Discriminación",
                "Resiliencia"
            ),
            Question(
                R.string.categoria_6_question_3,
                "Cívica",
                "Resiliente",
                "Resistente",
                "Ruda",
                "Madura"
            ),
            Question(
                R.string.categoria_6_question_4,
                "Cívica",
                "Comer tranquilamente",
                "No desayunar",
                "Comer menos",
                "Comer más rápido"
            ),
            Question(
                R.string.categoria_6_question_5,
                "Cívica",
                "Discriminación",
                "Procrastinación",
                "Diversidad",
                "Adversidad"
            )
        )
    }

    public val numOfQuestions
        get() = questions.size

    public val correctQuestions
        get() = questions.count { it -> it.esCorrecta == true }

    public val answeredQuestions
        get() = questions.count { it -> it.respondida == true }

    public fun getCurrentQuestion() = questions[currentQuestion]

    public fun getCurrentQuestionId() = currentQuestion

    public fun nextQuestion() {
        currentQuestion = (currentQuestion + 1) % questions.size
    }

    public fun prevQuestion() {
        currentQuestion = (currentQuestion + questions.size - 1) % questions.size
    }

    public fun getScore() : Int{
        score = correctQuestions * 100
        return score
    }

    public fun getPerformance(): Int {
        performance = correctQuestions * 100 / numOfQuestions
        return performance
    }

    public fun setQuestions(){
        questions = emptyList()
        for(x in listaCategorias){
            questions = questions.plus(allQuestions.filter { it.categoria == x })

        }
        questions.shuffled()
        //questions = questions.subList(0, numPreguntas)
    }

}