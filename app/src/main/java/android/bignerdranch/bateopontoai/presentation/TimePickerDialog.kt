package android.bignerdranch.bateopontoai.presentation

import android.app.TimePickerDialog
import android.bignerdranch.bateopontoai.data.AlarmItem
import android.bignerdranch.bateopontoai.data.HoursAndMinutes
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

@Composable
fun timePickerDialog(
    context: Context,
    timeState: MutableState<LocalDateTime?>,
    viewModel: MainScreenViewModel? = null
): TimePickerDialog {

    val calendar = Calendar.getInstance()
    val calendarHour = calendar[Calendar.HOUR_OF_DAY]
    val calendarMinute = calendar[Calendar.MINUTE]

    return TimePickerDialog(
        context,
        { _, hours: Int, minutes: Int ->

            val currentTime = LocalDate.from(LocalDate.now())
                .atTime(hours, minutes)

            timeState.value = currentTime

            viewModel?.entry1AlarmTime = AlarmItem("Entrada 1", currentTime)
            viewModel?.onEntry1Changed()
        }, calendarHour, calendarMinute, true
    )
}
