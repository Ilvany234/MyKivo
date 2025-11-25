package cl.duoc.kivo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.kivo.ui.auth.LoginScreen
import cl.duoc.kivo.ui.auth.RegisterScreen
import cl.duoc.kivo.ui.screens.*
import cl.duoc.kivo.ui.splash.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } } },
                onRegister = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegistered = { navController.navigate(Screen.Login.route) { popUpTo(Screen.Register.route) { inclusive = true } } },
                onBack = { navController.navigateUp() }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenProfile = { navController.navigate(Screen.Profile.route) },
                onOpenReviews = { navController.navigate(Screen.Reviews.route) },
                onOpenFavorites = { navController.navigate(Screen.Favorites.route) }
            )
        }
        composable(Screen.Profile.route) { ProfileScreen(onBack = { navController.navigateUp() }) }
        composable(Screen.Reviews.route) { ReviewsScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
    }
}
