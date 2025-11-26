package cl.duoc.kivo.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.duoc.kivo.ui.viewmodel.AuthViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onRegisterClick: () -> Unit, vm: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var msg by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Kivo", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(email, { email = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(pw, { pw = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = { vm.login(email, pw, onSuccess = { onLoginSuccess() }, onError = { msg = it }) }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("Iniciar sesión", color = MaterialTheme.colorScheme.onPrimary)
        }
        Spacer(Modifier.height(12.dp))
        Row { Text("¿No tienes cuenta? "); Text("Regístrate", color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { onRegisterClick() }) }
        msg?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}
