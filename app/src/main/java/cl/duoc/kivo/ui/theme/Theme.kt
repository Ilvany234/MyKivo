package cl.duoc.kivo.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun KivoTheme(content: @Composable () -> Unit) {
    val dynamic = true
    val useDark = false
    val colorScheme = if (dynamic && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (useDark) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(LocalContext.current)
    } else {
        lightColorScheme(
            primary = KivoOrange,
            onPrimary = KivoWhite,
            background = KivoYellow,
            onBackground = KivoText,
            surface = KivoWhite,
            onSurface = KivoText,
            secondary = KivoOrange
        )
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography(), content = content)
}
