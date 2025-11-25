package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReviewsScreen() {
    var reviewText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reseñas", style = MaterialTheme.typography.h6)
        OutlinedTextField(reviewText, { reviewText = it }, label = { Text("Escribe tu reseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Button(onClick = { /* Guardar review */ }) { Text("Agregar reseña") }
    }
}

