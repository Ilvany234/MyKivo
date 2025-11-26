package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.duoc.kivo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenLessons: () -> Unit, onOpenProfile: () -> Unit, onOpenReviews: () -> Unit, onOpenFavorites: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF3E0)).verticalScroll(rememberScrollState()).padding(16.dp)) {
        CenterAlignedTopAppBar(title = { Text("Kivo", color = Color.White) }, navigationIcon = { IconButton(onClick = {}) { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "menu") } }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary))
        Spacer(Modifier.height(24.dp))
        Button(onClick = onOpenLessons, modifier = Modifier.fillMaxWidth().height(52.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Ver Lecciones", color = MaterialTheme.colorScheme.onSecondary) }
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onOpenProfile, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Perfil", color = MaterialTheme.colorScheme.onSecondary) }
            Button(onClick = onOpenReviews, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Reseñas", color = MaterialTheme.colorScheme.onSecondary) }
            Button(onClick = onOpenFavorites, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Favoritos", color = MaterialTheme.colorScheme.onSecondary) }
        }
    }
}
