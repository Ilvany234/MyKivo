package cl.duoc.kivo

import cl.duoc.kivo.util.Validator
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    // --- PRUEBAS DE VALIDACIÓN DE CONTRASEÑA ---

    @Test
    fun `contraseña valida`() {
        val result = Validator.validatePassword("Password123")
        assertEquals("", result) // Una contraseña válida no debe devolver ningún error
    }

    @Test
    fun `contraseña demasiado corta`() {
        val result = Validator.validatePassword("Pass1")
        assertEquals("Mínimo 8 caracteres", result)
    }

    @Test
    fun `contraseña sin mayuscula`() {
        val result = Validator.validatePassword("password123")
        assertEquals("Debe contener una mayúscula", result)
    }

    @Test
    fun `contraseña sin minuscula`() {
        val result = Validator.validatePassword("PASSWORD123")
        assertEquals("Debe contener una minúscula", result)
    }

    @Test
    fun `contraseña sin numero`() {
        val result = Validator.validatePassword("Password")
        assertEquals("Debe contener un número", result)
    }

    // --- PRUEBAS DE VALIDACIÓN DE CORREO ---

    @Test
    fun `correo valido`() {
        val result = Validator.validateEmail("test@kivo.com")
        assertEquals("", result)
    }

    @Test
    fun `correo invalido sin arroba`() {
        val result = Validator.validateEmail("testkivo.com")
        assertEquals("Correo inválido", result)
    }

    // --- PRUEBAS DE VALIDACIÓN DE NOMBRE ---

    @Test
    fun `nombre valido`() {
        val result = Validator.validateName("Kivo User")
        assertEquals("", result)
    }

    @Test
    fun `nombre demasiado corto`() {
        val result = Validator.validateName("Ki")
        assertEquals("Debe tener al menos 3 caracteres", result)
    }
}
