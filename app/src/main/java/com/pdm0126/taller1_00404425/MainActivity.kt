package com.pdm0126.taller1_00404425

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.pdm0126.taller1_00404425.ui.screens.QuizScreen
import com.pdm0126.taller1_00404425.ui.screens.WelcomeScreen
import com.pdm0126.taller1_00404425.ui.screens.ResultScreen
import com.pdm0126.taller1_00404425.ui.theme.AndroidPediaByDerasTheme

//Controlador principal
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Renderiza la pantalla
        setContent {
            //Estados globales
            var screen by remember { mutableStateOf("welcome") }
            var finalScore by remember { mutableStateOf(0) }

            AndroidPediaByDerasTheme {

                when (screen) {

                    "welcome" -> {
                        WelcomeScreen(
                            onStartClick = {
                                screen = "quiz"
                            }
                        )
                    }

                    "quiz" -> {
                        QuizScreen(
                            onFinish = { score ->
                                finalScore = score
                                screen = "result"
                            }
                        )
                    }

                    "result" -> {
                        ResultScreen(
                            score = finalScore,
                            total = 3,
                            onRestart = {
                                screen = "welcome"
                                finalScore = 0
                            }
                        )
                    }
                }
            }
        }
    }
}