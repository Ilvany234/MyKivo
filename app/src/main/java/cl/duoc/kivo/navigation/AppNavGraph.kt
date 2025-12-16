package cl.duoc.kivo.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.kivo.ui.auth.LoginScreen
import cl.duoc.kivo.ui.auth.RegisterScreen
import cl.duoc.kivo.ui.screens.FavoritesScreen
import cl.duoc.kivo.ui.screens.HomeScreen
import cl.duoc.kivo.ui.screens.LessonsScreen
import cl.duoc.kivo.ui.screens.ProfileScreen
import cl.duoc.kivo.ui.screens.ReviewsScreen
import cl.duoc.kivo.ui.splash.SplashScreen
import cl.duoc.kivo.ui.viewmodel.AuthViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } } },
                onRegisterClick = { navController.navigate(Screen.Register.route) },
                vm = authViewModel
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegistered = { navController.navigate(Screen.Login.route) { popUpTo(Screen.Register.route) { inclusive = true } } },
                onBack = { navController.navigateUp() },
                vm = authViewModel
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenLessons = { navController.navigate(Screen.Lessons.route) },
                onOpenProfile = { navController.navigate(Screen.Profile.route) },
                onOpenReviews = { navController.navigate(Screen.Reviews.route) },
                onOpenFavorites = { navController.navigate(Screen.Favorites.route) },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Lessons.route) {
            LessonsScreen(onBack = { navController.navigateUp() })
        }
        composable(Screen.Profile.route) {
            // Pasamos datos de ejemplo al perfil. En un futuro, estos vendrían del ViewModel.
            ProfileScreen(userName = "Usuario Kivo", email = "test@kivo.com", onBack = { navController.navigateUp() })
        }
        composable(Screen.Reviews.route) { ReviewsScreen(onBack = { navController.navigateUp() }) }
        composable(Screen.Favorites.route) { FavoritesScreen(onBack = { navController.navigateUp() }) }
    }
}
