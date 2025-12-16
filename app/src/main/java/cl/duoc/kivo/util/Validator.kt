package cl.duoc.kivo.util

object Validator {

    fun validateName(name: String): String {
        return if (name.length < 3) "Debe tener al menos 3 caracteres" else ""
    }

    fun validateEmail(email: String): String {
        return if (!email.contains("@")) "Correo inválido" else ""
    }

    fun validatePassword(password: String): String {
        return when {
            password.length < 8 -> "Mínimo 8 caracteres"
            !password.any { it.isUpperCase() } -> "Debe contener una mayúscula"
            !password.any { it.isLowerCase() } -> "Debe contener una minúscula"
            !password.any { it.isDigit() } -> "Debe contener un número"
            else -> ""
        }
    }
}
