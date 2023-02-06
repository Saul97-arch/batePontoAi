package android.bignerdranch.bateopontoai.presentation

import android.bignerdranch.bateopontoai.data.HoursAndMinutes
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimeButton(
    context: Context,
    timeState: MutableState<LocalDateTime?>,
    text: String,
    viewModel: MainScreenViewModel,
) {
    val timePickerDialog = timePickerDialog(
        context = context,
        timeState = timeState,
        viewModel
    )

    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    return Button(
        onClick = {
            timePickerDialog.show()
        },
        modifier = Modifier
            .fillMaxWidth(0.8f),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(
                247,
                54,
                62
            )
        ),
    ) {
        Text(
            text = timeState.value?.format(formatter) ?: text
        )
    }
}
