package android.bignerdranch.bateopontoai.presentation

import android.app.TimePickerDialog
import android.bignerdranch.bateopontoai.data.HoursAndMinutes
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun timePickerDialog(
    context: Context,
    timeState: MutableState<HoursAndMinutes>,
    viewModel: MainScreenViewModel? = null
): TimePickerDialog {

    val calendar = Calendar.getInstance()
    val calendarHour = calendar[Calendar.HOUR_OF_DAY]
    val calendarMinute = calendar[Calendar.MINUTE]

    return TimePickerDialog(
        context,
        { _, hours: Int, minutes: Int ->
            timeState.value = HoursAndMinutes(hours, minutes)
            viewModel?.onEntry1Changed()
        }, calendarHour, calendarMinute, true
    )
}
