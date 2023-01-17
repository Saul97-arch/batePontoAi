package android.bignerdranch.bateopontoai.presentation

import android.bignerdranch.bateopontoai.data.HoursAndMinutes
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.O)
class MainScreenViewModel : ViewModel() {

    var lunchTime: Int = 1
    var round1: Int = 3
    var round2: Int = 5

    val entry1 = mutableStateOf(HoursAndMinutes())
    val out1 = mutableStateOf(HoursAndMinutes())
    val entry2 = mutableStateOf(HoursAndMinutes())
    val out2 = mutableStateOf(HoursAndMinutes())

    // TODO search for various alarm settings
    // for instance 3 diferent alarm times created

    // USE CASES
    // For instance
    // I entered in my work at 9 am
    // I was suposed to stop my work at 12

    fun onEntry1Changed() {
        val hours = entry1.value.hours
        val minutes = entry1.value.minutes

        val newHours = hours?.plus(round1)

        out1.value = HoursAndMinutes(newHours, minutes)

        entry2.value = HoursAndMinutes(out1.value.hours?.plus(lunchTime), out1.value.minutes)

        out2.value = HoursAndMinutes(entry2.value.hours?.plus(round2), entry2.value.minutes)
    }
}
