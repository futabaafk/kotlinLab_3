package com.example.campuscardlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campuscardlab.ui.theme.CampusCardLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusCardLabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CampusCardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CampusCardScreen(modifier: Modifier = Modifier) {
    //  2 переключатель между двумя вариантами нижней панели
    var useSpaceBetween by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Шапка Row с аватаром и вложенным Column
        ProfileHeader(name = "Sultanbek", status = "In class · Building B")

        Spacer(modifier = Modifier.height(16.dp))

        // 1 блок заголовка по центру, остальное остаётся слева
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Campus Card", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Sultanbek")
            Text(text = "Student ID: 2160")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Today", style = MaterialTheme.typography.titleLarge)

        // Список занятий три строки Row
        Column(modifier = Modifier.fillMaxWidth()) {
            ScheduleRow(time = "09:30", title = "Mobile dev", room = "online")
            ScheduleRow(time = "11:05", title = "Sport programming", room = "online")
            // Exercise 4: длинное название для проверки weight(1f)
            ScheduleRow(
                time = "14:30",
                title = "Programming languages",
                room = "B305"
            )
        }

        // Толкает нижнюю панель к низу экрана
        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = { useSpaceBetween = !useSpaceBetween }) {
            Text(text = if (useSpaceBetween) "Mode: SpaceBetween" else "Mode: Center")
        }

        // Нижняя панель Row с двумя кнопками
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (useSpaceBetween) {
                Arrangement.SpaceBetween
            } else {
                Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            }
        ) {
            Button(onClick = { }) { Text("Map") }
            Button(onClick = { }) { Text("Show QR") }
        }
    }
}

//  5 вынесенные composable-функции
@Composable
fun ProfileHeader(name: String, status: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Column {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = status)
        }
    }
}

@Composable
fun ScheduleRow(time: String, title: String, room: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        //  3  Alignment.Top, затем Alignment.CenterVertically
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Время крупнее названия
        Text(text = time, style = MaterialTheme.typography.titleLarge)
        //  4git название занимает всё свободное место между временем и аудиторией
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = room)
    }
}

@Preview(showBackground = true)
@Composable
fun CampusCardPreview() {
    CampusCardLabTheme {
        CampusCardScreen()
    }
}