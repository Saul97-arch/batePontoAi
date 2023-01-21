package android.bignerdranch.bateopontoai.presentation

import android.bignerdranch.bateopontoai.data.AlarmItem
import android.bignerdranch.bateopontoai.data.AndroidAlarmScheduler
import android.bignerdranch.bateopontoai.data.HoursAndMinutes
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainScreenViewModel : ViewModel() {

    var lunchTime: Int = 1
    var round1: Int = 3
    var round2: Int = 5

    val entry1 = mutableStateOf(HoursAndMinutes())
    val out1 = mutableStateOf(HoursAndMinutes())
    val entry2 = mutableStateOf(HoursAndMinutes())
    val out2 = mutableStateOf(HoursAndMinutes())

    lateinit var scheduler: AndroidAlarmScheduler

    var entry1AlarmTime: AlarmItem? = null
    var out1AlarmTime: AlarmItem? = null
    var entry2AlarmTime : AlarmItem? = null
    var out2AlarmTime : AlarmItem? = null

    // TODO search for various alarm settings
    // for instance 3 diferent alarm times created

    // USE CASES
    // For instance
    // I entered in my work at 9 am
    // I was suposed to stop my work at 12

    fun onEntry1Changed() {
        val entry1Hours = entry1.value.hours
        val entry1Minutes = entry1.value.minutes

        setOut1Value(entry1Hours, entry1Minutes)
        setEntry2Value()
        setOut2Value()

        //out1AlarmTime?.let { scheduler.schedule(it) }
        //entry2AlarmTime?.let { scheduler.schedule(it) }
        out2AlarmTime?.let { scheduler.schedule(it) }
    }
    // todo get value in millis and pass to current time to verify
    private fun setOut1Value(hours: Int?, minutes: Int?) {
        out1AlarmTime = AlarmItem(
            "Saída 1",
            entry1AlarmTime?.timeInMilliseconds?.plus(round1 * 3600 * 1000)
        )
        out1.value = HoursAndMinutes(hours?.plus(round1), minutes)
        println("saida 1 " + returnCurrentHour(out1AlarmTime?.timeInMilliseconds))
    }

    private fun setEntry2Value() {
        entry2AlarmTime = AlarmItem(
            "Entrada 2 ",
            out1AlarmTime?.timeInMilliseconds?.plus(lunchTime * 3600 * 1000)
        )
        entry2.value = HoursAndMinutes(out1.value.hours?.plus(lunchTime), out1.value.minutes)
        println("entrada 2 " + returnCurrentHour(entry2AlarmTime?.timeInMilliseconds))
    }

    private fun setOut2Value() {
        out2AlarmTime = AlarmItem(
            "Saída 2",
            entry2AlarmTime?.timeInMilliseconds?.plus(round2 * 3600 * 1000)
        )
        out2.value = HoursAndMinutes(entry2.value.hours?.plus(round2), entry2.value.minutes)
        println("saida 2 " + returnCurrentHour(out2AlarmTime?.timeInMilliseconds))
    }

    private fun returnCurrentHour(hour : Long?) = SimpleDateFormat("HH:mm").format(hour?.let {
        Date(
            it
        )
    })
}
