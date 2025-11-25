package cl.duoc.kivo.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

data class Section(val level: String, val lesson: String, val imageName: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenProfile: () -> Unit, onOpenReviews: () -> Unit, onOpenFavorites: () -> Unit) {
    val context = LocalContext.current
    val sections = remember {
        listOf(
            Section("Principiante", "Lección 1", "img_intro"),
            Section("Inicial", "Lección 2", "img_saludos"),
            Section("Intermedio", "Lección 3", "img_vocab"),
            Section("Avanzado", "Lección 4", "img_frases"),
            Section("Master", "Lección 5", "img_conv")
        )
    }

    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) Toast.makeText(context, "Muy buen trabajo!", Toast.LENGTH_LONG).show()
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Kivo") }, backgroundColor = MaterialTheme.colors.secondary) }) { padding ->
        LazyColumn(contentPadding = padding, modifier = Modifier.fillMaxSize()) {
            items(sections) { s ->
                Card(modifier = Modifier.fillMaxWidth().padding(12.dp), elevation = 4.dp) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(s.level)
                        Text(s.lesson)
                        Spacer(Modifier.height(6.dp))
                        Box(modifier = Modifier.height(160.dp).fillMaxWidth(), contentAlignment = Alignment.Center) { Text("Imagen: ${s.imageName}") }
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { takePictureLauncher.launch(null) }) { Text("Abrir cámara") }
                    }
                }
            }
        }
    }
}
