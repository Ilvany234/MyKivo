package cl.duoc.kivo.data

import cl.duoc.kivo.R

data class Lesson(
    val id: Int,
    val level: String,
    val title: String,
    val description: String,
    val image: Int
)

val lessonsList = listOf(
    Lesson(
        id = 1,
        level = "Nivel Principiante",
        title = "Lección 1: Introducción al Lenguaje de Señas",
        description = "En esta lección aprenderás la historia del lenguaje de señas, su importancia en la inclusión y las normas básicas para comunicarte de manera respetuosa con la comunidad sorda.",
        image = R.drawable.leccion_1
    ),
    Lesson(
        id = 2,
        level = "Nivel Inicial",
        title = "Lección 2: El Alfabeto Manual",
        description = "Aprende a representar cada letra del abecedario con tus manos. Esta base es fundamental para deletrear nombres y palabras que no tienen un signo propio.",
        image = R.drawable.leccion_2
    ),
    Lesson(
        id = 3,
        level = "Nivel Inicial",
        title = "Lección 3: Saludos Básicos",
        description = "Aprende a saludar, despedirte y presentarte en lengua de señas, usando expresiones cotidianas y amigables.",
        image = R.drawable.leccion_3
    ),
    Lesson(
        id = 4,
        level = "Nivel Intermedio",
        title = "Lección 4: Emociones y Estados",
        description = "Identifica y expresa emociones esenciales como felicidad, tristeza, sorpresa y más mediante señas.",
        image = R.drawable.leccion_4
    ),
    Lesson(
        id = 5,
        level = "Nivel Intermedio",
        title = "Lección 5: Números",
        description = "Aprende a representar números del 1 al 100 con señas claras y correctas.",
        image = R.drawable.leccion_5
    )
)
