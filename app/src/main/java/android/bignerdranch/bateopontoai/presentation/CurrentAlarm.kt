package android.bignerdranch.bateopontoai.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.format.DateTimeFormatter

@Composable
fun CurrentAlarm(viewModel: MainScreenViewModel) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Próximo alarme", color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = viewModel.getCurrentAlarmTimeData()?.value?.localDateTime?.format(formatter)
                ?: "A definir",
            color = Color.White
        )
    }
}
