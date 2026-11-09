package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(
    words: List<String> = listOf("ANDROID", "COMPOSE", "KOTLIN", "STUDIO", "DESIGN")
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        words.forEachIndexed { rowIndex, word ->
            val rowNumber = rowIndex + 1
            val isEvenRow = rowNumber % 2 == 0

            val yellowIndices = remember(word, rowIndex) {
                if (isEvenRow && word.isNotEmpty()) {
                    val countToHighlight = Random.nextInt(1, (word.length / 2 + 1).coerceAtLeast(2))
                    word.indices.shuffled().take(countToHighlight).toSet()
                } else {
                    emptySet()
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                word.forEachIndexed { charIndex, char ->
                    val backgroundColor = when {
                        isEvenRow && charIndex in yellowIndices -> Color.Yellow
                        isEvenRow -> Color.LightGray
                        else -> MaterialTheme.colorScheme.primary
                    }

                    val textColor = when {
                        isEvenRow && charIndex in yellowIndices -> Color.Black
                        isEvenRow -> Color.DarkGray
                        else -> MaterialTheme.colorScheme.onPrimary
                    }

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(6.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char.toString().uppercase(),
                            color = textColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Greeting()
    }
}