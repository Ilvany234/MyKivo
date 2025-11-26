package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import cl.duoc.kivo.data.lessonsList
import cl.duoc.kivo.data.Lesson

@Composable
fun LessonsScreen(onOpenCamera: () -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF0D7))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        items(lessonsList) { lesson ->
            LessonCard(lesson, onOpenCamera)
        }
    }
}

@Composable
fun LessonCard(lesson: Lesson, onOpenCamera: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(22.dp)) {

            // Nivel
            Text(
                lesson.level,
                fontSize = 20.sp,
                color = Color(0xFFE67E22),
                fontWeight = FontWeight.Bold
            )

            // Título de lección
            Text(
                lesson.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFCA6F1E)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Imagen de la lección
            Image(
                painter = painterResource(id = lesson.image),
                contentDescription = lesson.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Descripción
            Text(
                lesson.description,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botón
            Button(
                onClick = { onOpenCamera() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(45.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA726)
                )
            ) {
                Text("Abrir Cámara", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
