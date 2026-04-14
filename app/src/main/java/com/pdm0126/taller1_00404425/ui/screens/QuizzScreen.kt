package com.pdm0126.taller1_00404425.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.pdm0126.taller1_00404425.data.quizQuestions

@Composable
fun QuizScreen(
    onFinish: (Int) -> Unit
) {
    //Selecciono pregunticas

    var selectedQuestions by remember {
        mutableStateOf(quizQuestions.shuffled().take(3))
    }
    //Estados del juego
    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var answered by remember { mutableStateOf(false) }

    //Preguntica actual
    val currentQuestion = selectedQuestions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        //Pregunta
        Text(
            text = "Pregunta ${currentIndex + 1} de ${selectedQuestions.size}",
            style = MaterialTheme.typography.titleMedium
        )

        //Puntaje
        Text(
            text = "Puntaje: $score / ${selectedQuestions.size}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Tarjeta de preguntica
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = currentQuestion.question,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        currentQuestion.options.forEach { option ->
            //Comparación
            val isCorrect = option == currentQuestion.correctAnswer
            val isSelected = option == selectedOption
            //Manejo colores
            val backgroundColor = when {
                isCorrect -> Color(0xFF4CAF50) // verde
                isSelected && !isCorrect -> Color(0xFFF44336) // rojo
                else -> Color.LightGray // neutro
            }

            //Acá evito que cambie de respuesta

            Button(
                onClick = {
                    if (!answered) {
                        selectedOption = option
                        answered = true

                        if (isCorrect) {
                            score++
                        }
                    }
                },
                enabled = !answered,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White,
                    disabledContainerColor = backgroundColor,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Text(text = option)
            }
        }


        if (answered) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentQuestion.funFact,
                style = MaterialTheme.typography.bodyMedium
            )
        }


        if (answered) {
            Spacer(modifier = Modifier.height(20.dp))
            //Botón de resultado/siguiente
            Button(
                onClick = {
                    if (currentIndex < selectedQuestions.size - 1) {
                        currentIndex++
                        selectedOption = null
                        answered = false
                    } else {
                        onFinish(score)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (currentIndex < selectedQuestions.size - 1)
                        "Siguiente"
                    else
                        "Ver Resultado"
                )
            }
        }
    }
}