package cl.duoc.kivo.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ÚNICA paleta de colores. Se usará siempre.
private val KivoColorScheme = lightColorScheme(
    primary = KivoOrange,       // Naranja para TopAppBar, botones, etc.
    onPrimary = KivoText,
    secondary = KivoOrangeLight,
    onSecondary = KivoText,
    background = KivoCreme,      // Fondo de pantalla crema
    surface = KivoCremeDark,
    onBackground = KivoText,
    onSurface = KivoText
)

@Composable
fun KivoTheme(content: @Composable () -> Unit) { // Se elimina por completo la detección de tema oscuro

    // Siempre y únicamente se usa nuestro esquema de color.
    val colorScheme = KivoColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            
            // La barra de estado superior (hora, batería) es del color del fondo
            window.statusBarColor = colorScheme.background.toArgb()
            // Los iconos de la barra de estado (hora, batería) son oscuros para ser legibles
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
