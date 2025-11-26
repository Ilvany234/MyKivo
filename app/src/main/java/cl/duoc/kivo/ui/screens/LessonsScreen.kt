package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.duoc.kivo.data.Lesson
import cl.duoc.kivo.data.lessonsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonsScreen(onBack: () -> Unit, onOpenCamera: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lecciones") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Volver") } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lessonsList) { lesson ->
                LessonCard(lesson = lesson, onOpenCamera = onOpenCamera)
            }
        }
    }
}

@Composable
fun LessonCard(lesson: Lesson, onOpenCamera: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = lesson.level,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = lesson.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Image(
                painter = painterResource(id = lesson.image),
                contentDescription = lesson.title,
                modifier = Modifier.fillMaxWidth().height(180.dp).clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(12.dp))
            Text(text = lesson.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onOpenCamera,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Abrir Cámara")
            }
        }
    }
}
