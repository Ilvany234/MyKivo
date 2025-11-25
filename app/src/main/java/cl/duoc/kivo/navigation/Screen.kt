package cl.duoc.kivo.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Reviews : Screen("reviews")
    object Favorites : Screen("favorites")
}
