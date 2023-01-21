package android.bignerdranch.bateopontoai

import android.bignerdranch.bateopontoai.data.AlarmItem

interface AlarmScheduler {
    fun schedule(item : AlarmItem)
    fun cancel(item : AlarmItem)
}
