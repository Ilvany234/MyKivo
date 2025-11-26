package cl.duoc.kivo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.kivo.R

@Composable
fun ProfileScreen(
    userName: String,
    email: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(60.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(userName, fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(email, fontSize = 16.sp, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(20.dp))

        Text("Información de la cuenta", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))

        Text("Nombre: $userName")
        Text("Correo: $email")
        Text("Registrado en Kivo")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}
