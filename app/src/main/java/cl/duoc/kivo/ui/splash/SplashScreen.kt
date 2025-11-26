package cl.duoc.kivo.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.duoc.kivo.navigation.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import cl.duoc.kivo.ui.viewmodel.SplashViewModel

@Composable
fun SplashScreen(navController: NavController, vm: SplashViewModel = hiltViewModel()) {
    // --- ¡SOLUCIÓN! ---
    // Usamos la nueva variable 'isLoggedIn' en lugar de 'hasUser'
    val isLoggedIn by vm.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn != null) {
            if (isLoggedIn == true) {
                navController.navigate(Screen.Home.route) { popUpTo(Screen.Splash.route) { inclusive = true } }
            } else {
                navController.navigate(Screen.Login.route) { popUpTo(Screen.Splash.route) { inclusive = true } }
            }
        }
    }
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Kivo")
            Spacer(modifier = Modifier.height(12.dp))
            CircularProgressIndicator()
        }
    }
}
