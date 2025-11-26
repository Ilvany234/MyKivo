package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.duoc.kivo.data.local.entities.ReviewEntity
import cl.duoc.kivo.ui.viewmodel.ReviewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsScreen(onBack: () -> Unit, vm: ReviewsViewModel = hiltViewModel()) {
    
    // Observamos la lista de reseñas desde el ViewModel
    val reviews by vm.reviews.collectAsState()
    var newReviewText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reseñas") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Volver") } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it).padding(16.dp)) {
            OutlinedTextField(
                value = newReviewText,
                onValueChange = { newReviewText = it },
                label = { Text("Escribe tu reseña...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { 
                    // Llamamos a la función del ViewModel para añadir la reseña
                    vm.addReview(newReviewText)
                    newReviewText = "" // Limpiamos el campo de texto
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = newReviewText.isNotBlank()
            ) {
                Text("Agregar reseña")
            }
            Spacer(Modifier.height(16.dp))
            // Mostramos la lista de reseñas REALES de la base de datos
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(reviews) { review ->
                    ReviewCard(review)
                }
            }
        }
    }
}

@Composable
fun ReviewCard(review: ReviewEntity) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(review.author, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(4.dp))
            Text(review.text)
        }
    }
}
