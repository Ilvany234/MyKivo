package cl.duoc.kivo.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.duoc.kivo.ui.viewmodel.AuthViewModel
import cl.duoc.kivo.ui.viewmodel.UiState

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onRegisterClick: () -> Unit, vm: AuthViewModel = hiltViewModel()) {

    val uiState by vm.uiState.collectAsState()

    // Efecto para manejar la navegación y reseteo de estado
    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            onLoginSuccess()
            vm.resetState()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Kivo", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(16.dp))

        // Campo de Correo (conectado al ViewModel)
        OutlinedTextField(
            value = vm.correo.value.valor,
            onValueChange = { vm.onCorreoChange(it) },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))

        // Campo de Contraseña (conectado al ViewModel)
        OutlinedTextField(
            value = vm.clave.value.valor,
            onValueChange = { vm.onClaveChange(it) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(24.dp))

        // Botón de Login y Estado de Carga
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            if (uiState is UiState.Loading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = { vm.login() }, // Llama a la nueva función sin parámetros
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Iniciar sesión", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
        Spacer(Modifier.height(12.dp))

        // Link a Registro
        Row {
            Text("¿No tienes cuenta? ")
            Text("Regístrate", color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { onRegisterClick() })
        }

        // Mensaje de Error
        if (uiState is UiState.Error) {
            Spacer(Modifier.height(8.dp))
            Text((uiState as UiState.Error).message, color = MaterialTheme.colorScheme.error)
        }
    }
}
