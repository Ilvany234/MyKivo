package cl.duoc.kivo.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.duoc.kivo.ui.viewmodel.AuthViewModel
import cl.duoc.kivo.ui.viewmodel.UiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegistered: () -> Unit, onBack: () -> Unit, vm: AuthViewModel = hiltViewModel()) {

    val uiState by vm.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is UiState.Success -> {
                scope.launch { snackbarHostState.showSnackbar("¡Registro exitoso!") }
                onRegistered()
                vm.resetState()
            }
            is UiState.Error -> {
                scope.launch { snackbarHostState.showSnackbar(state.message) }
                vm.resetState()
            }
            else -> Unit
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        Column(modifier = Modifier.fillMaxSize().padding(it).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Text("Regístrate en Kivo", fontSize = 24.sp, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))

            RegisterTextField(label = "Nombre", state = vm.nombre.value, onValueChange = vm::onNombreChange)
            RegisterTextField(label = "Correo", state = vm.correo.value, onValueChange = vm::onCorreoChange)
            RegisterTextField(label = "Contraseña", state = vm.clave.value, onValueChange = vm::onClaveChange, isPassword = true)
            RegisterTextField(label = "Edad", state = vm.edad.value, onValueChange = vm::onEdadChange)

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = vm.terminos.value, onCheckedChange = { vm.onTerminosChange(it) }) // Colores heredados del tema
                Text("Acepto términos y condiciones")
            }
            Spacer(Modifier.height(16.dp))

            val isFormValid = vm.nombre.value.error.isEmpty() && vm.correo.value.error.isEmpty() && vm.clave.value.error.isEmpty() && vm.edad.value.error.isEmpty() && vm.terminos.value
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (uiState is UiState.Loading) {
                    CircularProgressIndicator()
                } else {
                    Button(onClick = { vm.register() }, enabled = isFormValid, modifier = Modifier.fillMaxWidth()) {
                        Text("Registrarse") // Colores de texto y botón heredados
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            Text("Volver al inicio", color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { onBack() })
        }
    }
}

@Composable
private fun RegisterTextField(label: String, state: AuthViewModel.CampoEstado, onValueChange: (String) -> Unit, isPassword: Boolean = false) {
    OutlinedTextField(
        value = state.valor,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
        singleLine = true,
        isError = state.tocado && state.error.isNotEmpty(),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        supportingText = { if (state.tocado && state.error.isNotEmpty()) Text(state.error) }
    )
}
