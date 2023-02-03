package android.bignerdranch.bateopontoai.data

import androidx.compose.runtime.MutableState

interface Repository {
    val currentAlarmState : MutableState<AlarmItem>
    fun getCurrentAlarmStateData() : MutableState<AlarmItem> {
        return currentAlarmState
    }
    fun setCurrentAlarmStateData(alarmItem: AlarmItem) {
        currentAlarmState.value = alarmItem
    }
}