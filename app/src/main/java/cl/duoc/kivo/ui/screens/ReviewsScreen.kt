package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ReviewsScreen(onBack: () -> Unit) {
    var reviewText by remember { mutableStateOf("") }
    val reviews = remember { mutableStateListOf("Muy buena app", "Excelente aprendizaje") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reseñas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(value = reviewText, onValueChange = { reviewText = it }, modifier = Modifier.fillMaxWidth().height(100.dp).border(1.dp, MaterialTheme.colorScheme.primary))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (reviewText.isNotBlank()) {
                reviews.add(reviewText)
                reviewText = ""
            }
        }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))) {
            Text("Agregar reseña")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Reseñas existentes:")
        reviews.forEach { Text("- $it") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB74D))) {
            Text("Volver")
        }
    }
}

