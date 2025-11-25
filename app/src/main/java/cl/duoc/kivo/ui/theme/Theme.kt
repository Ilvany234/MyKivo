package cl.duoc.kivo.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColors(
    primary = KivoOrange,
    primaryVariant = KivoOrangeDark,
    secondary = KivoOrange,
    background = KivoYellow,
    surface = KivoWhite,
    onPrimary = KivoWhite,
    onSecondary = KivoWhite,
    onBackground = KivoText,
    onSurface = KivoText
)

@Composable
fun KivoTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = LightColors, content = content)
}

