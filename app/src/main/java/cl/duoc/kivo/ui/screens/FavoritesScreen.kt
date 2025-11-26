package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(onBack: () -> Unit) {
    var word by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    val list = remember { mutableStateListOf<Pair<String,String>>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favoritos") },
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
                value = word,
                onValueChange = { word = it },
                label = { Text("Palabra favorita") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = { if (word.isNotBlank()) { list.add(0, word to desc); word = ""; desc = "" } }, modifier = Modifier.fillMaxWidth()) {
                Text("Agregar a favoritos")
            }
            Spacer(Modifier.height(24.dp))
            Text("Tus favoritos:", style = MaterialTheme.typography.titleMedium)
            list.forEach { fav ->
                Text("- ${fav.first}: ${fav.second}")
            }
        }
    }
}
