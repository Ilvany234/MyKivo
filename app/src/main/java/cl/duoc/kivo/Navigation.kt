package cl.duoc.kivo
// Navigation.kt - Jetpack Compose Navigation setup

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.duoc.kivo.ui.screens.HomeScreen
import cl.duoc.kivo.ui.screens.LoginScreen
import cl.duoc.kivo.ui.screens.RegisterScreen
import cl.duoc.kivo.ui.splash.SplashScreen

// ----------------------
// Rutas de navegación
// ----------------------
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
}

// ----------------------
// Gráfico de navegación
// ----------------------
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Pantalla de carga inicial
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        // Pantalla Login
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        // Pantalla Registro
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        // Pantalla Home
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}

