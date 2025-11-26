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

@Composable
fun SplashScreen(navController: NavController, vm: cl.duoc.kivo.ui.viewmodel.SplashViewModel = hiltViewModel()) {
    val hasUser by vm.hasUser.collectAsState()
    LaunchedEffect(hasUser) {
        if (hasUser != null) {
            if (hasUser == true) navController.navigate(Screen.Home.route) { popUpTo(Screen.Splash.route){ inclusive = true } }
            else navController.navigate(Screen.Login.route) { popUpTo(Screen.Splash.route){ inclusive = true } }
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
