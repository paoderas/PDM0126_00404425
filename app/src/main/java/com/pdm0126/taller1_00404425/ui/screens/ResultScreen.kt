package com.pdm0126.taller1_00404425.ui.screens



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    //función
    onRestart: () -> Unit
) {
    //mensaje dinámico
    val message = when (score) {
        3 -> "¡Perfecto!"
        2 -> "Puedes mejorar"
        1 -> "Sigue practicando"
        else -> "Repasa la historia de Android"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Mostrar resultado
        Text(
            text = "Obtuviste $score de $total",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        //Ejecutamos el onRestart()
        Button(
            onClick = { onRestart() }
        ) {
            Text("Reiniciar Quiz")
        }
    }
}