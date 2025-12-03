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

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // ----------- SPLASH -----------
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        // ----------- LOGIN -----------
        composable(Screen.Login.route) {
            LoginScreen(
                vm = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Screen.Register.route) }
            )
        }

        // ----------- REGISTER -----------
        composable(Screen.Register.route) {
            RegisterScreen(
                vm = authViewModel,
                onRegistered = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onBack = { navController.navigateUp() }
            )
        }

        // ----------- HOME -----------
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenLessons = { navController.navigate(Screen.Lessons.route) },
                onOpenProfile = { navController.navigate(Screen.Profile.route) },
                onOpenReviews = { navController.navigate(Screen.Reviews.route) },
                onOpenFavorites = { navController.navigate(Screen.Favorites.route) },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ----------- LESSONS -----------
        composable(Screen.Lessons.route) {
            LessonsScreen(
                onBack = { navController.navigateUp() },
                onOpenCamera = { /* acción futura */ }
            )
        }

        // ----------- PROFILE -----------
        composable(Screen.Profile.route) {
            ProfileScreen(
                vm = authViewModel,
                onBack = { navController.navigateUp() }
            )
        }

        // ----------- REVIEWS -----------
        composable(Screen.Reviews.route) {
            ReviewsScreen(onBack = { navController.navigateUp() })
        }

        // ----------- FAVORITES -----------
        composable(Screen.Favorites.route) {
            FavoritesScreen(onBack = { navController.navigateUp() })
        }
    }
}
