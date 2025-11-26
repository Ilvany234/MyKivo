package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(onBack: () -> Unit) {
    var word by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    val list = remember { mutableStateListOf<Pair<String,String>>() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Favoritos", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(word, { word = it }, label = { Text("Palabra favorita") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(desc, { desc = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth().height(120.dp))
        Spacer(Modifier.height(8.dp))
        Button(onClick = { if (word.isNotBlank()) { list.add(0, word to desc); word = ""; desc = "" } }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Agregar a favoritos") }
        Spacer(Modifier.height(12.dp))
        Text("Tus favoritos:")
        list.forEach { Text("- ${it.first}: ${it.second}") }
        Spacer(Modifier.height(12.dp))
        Button(onClick = onBack) { Text("Volver") }
    }
}
