package android.bignerdranch.bateopontoai.presentation

import android.bignerdranch.bateopontoai.data.AlarmItem
import android.bignerdranch.bateopontoai.data.AndroidAlarmScheduler
import android.bignerdranch.bateopontoai.data.Repository
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

class MainScreenViewModel : ViewModel() {

    var repository: Repository? = null
    private var lunchTime: Long = 1L
    private var round1: Long = 3L
    private var round2: Long = 5L

    val entry1: MutableState<LocalDateTime?> = mutableStateOf(null)
    val out1: MutableState<LocalDateTime?> = mutableStateOf(null)
    val entry2: MutableState<LocalDateTime?> = mutableStateOf(null)
    val out2: MutableState<LocalDateTime?> = mutableStateOf(null)

    lateinit var scheduler: AndroidAlarmScheduler

    var entry1AlarmTime: AlarmItem? = null
    var out1AlarmTime: AlarmItem? = null
    var entry2AlarmTime: AlarmItem? = null
    var out2AlarmTime: AlarmItem? = null

    fun onEntry1Changed() {
        setOut1Value(entry1)
        setEntry2Value()
        setOut2Value()
        setCurrentAlarm()
    }

    fun getCurrentAlarmTimeData(): MutableState<AlarmItem>? {
        return repository?.getCurrentAlarmStateData()
    }

    private fun setCurrentAlarmTimeData(alarmItem: AlarmItem) {
        repository?.setCurrentAlarmStateData(alarmItem)
    }

    private fun setOut1Value(entry1: MutableState<LocalDateTime?>) {
        out1AlarmTime = AlarmItem(
            "Saída 1",
            entry1AlarmTime?.localDateTime?.plusHours(round1)
        )
        out1.value = entry1.value?.plusHours(round1)
        out1AlarmTime?.let { scheduler.schedule(it) }
    }

    private fun setEntry2Value() {
        entry2AlarmTime = AlarmItem(
            "Entrada 2 ",
            out1AlarmTime?.localDateTime?.plusHours(lunchTime)
        )
        entry2AlarmTime?.let { scheduler.schedule(it) }
        entry2.value = out1.value?.plusHours(lunchTime)
    }

    private fun setOut2Value() {
        out2AlarmTime = AlarmItem(
            "Saída 2",
            entry2AlarmTime?.localDateTime?.plusHours(round2)
        )
        out2AlarmTime?.let { scheduler.schedule(it) }
        out2.value = entry2.value?.plusHours(round2)
    }

    private fun setCurrentAlarm() {
        val now = LocalDateTime.now()

        if (
            now.isBefore(out1AlarmTime?.localDateTime)
        ) {
            setCurrentAlarmTimeData(AlarmItem("saída 1", out1AlarmTime?.localDateTime))
        }

        if (
            !now.isBefore(out1AlarmTime?.localDateTime)
            && now.isBefore(entry2AlarmTime?.localDateTime)
        ) {
            setCurrentAlarmTimeData(AlarmItem("entrada 2", entry2AlarmTime?.localDateTime))
        }

        if (
            !now.isBefore(entry2AlarmTime?.localDateTime)
            && now.isBefore(out2AlarmTime?.localDateTime)
        ) {
            setCurrentAlarmTimeData(AlarmItem("saída 2", out2AlarmTime?.localDateTime))
        }
    }
}
