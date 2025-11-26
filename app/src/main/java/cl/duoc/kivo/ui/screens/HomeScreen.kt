package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.duoc.kivo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenLessons: () -> Unit,
    onOpenProfile: () -> Unit,
    onOpenReviews: () -> Unit,
    onOpenFavorites: () -> Unit,
    onLogout: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        CenterAlignedTopAppBar(
            title = { Text("Kivo") }, // El color se hereda de onPrimaryContainer
            navigationIcon = { IconButton(onClick = {}) { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "menu") } },
            actions = {
                IconButton(onClick = onLogout) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
                }
            },
            // Los colores de la barra se heredan del tema
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        Spacer(Modifier.height(24.dp))

        // Botones que usan el color primario del tema
        Button(onClick = onOpenLessons, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Ver Lecciones")
        }
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            // Botones que usan el color secundario del tema
            Button(onClick = onOpenProfile, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Perfil")
            }
            Button(onClick = onOpenReviews, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Reseñas")
            }
            Button(onClick = onOpenFavorites, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text("Favoritos")
            }
        }
    }
}
