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

@Composable
fun TimeButton(
    context: Context,
    timeState: MutableState<HoursAndMinutes>,
    text: String,
    viewModel: MainScreenViewModel,
) {
    // TODO time converter to proper string time
    // 9:0 to 9:00
    // 12:0 to 12:00
    val timePickerDialog = timePickerDialog(
        context = context,
        timeState = timeState,
        viewModel
    )

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
            text =
            if (timeState.value.hours == null && timeState.value.minutes == null) {
                text
            } else {
                "${timeState.value.hours}:${timeState.value.minutes}"
            }
        )
    }
}
