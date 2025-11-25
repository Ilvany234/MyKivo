package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text("Nombre del Usuario")
        Text("correo@ejemplo.com")
        Spacer(Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Información de la cuenta", style = MaterialTheme.typography.h6)
                Text("Nombre: Nombre del Usuario")
                Text("Correo: correo@ejemplo.com")
            }
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = onBack) { Text("Volver a lecciones") }
    }
}
