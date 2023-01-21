package android.bignerdranch.bateopontoai.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.bignerdranch.bateopontoai.AlarmReceiver
import android.bignerdranch.bateopontoai.AlarmScheduler
import android.content.Context
import android.content.Intent

class AndroidAlarmScheduler(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: AlarmItem) {

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", item.alarmName)
        }
        // 5 sec
        item.timeInMilliseconds?.let {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                it,
                AlarmManager.INTERVAL_DAY,
                PendingIntent.getBroadcast(
                    context,
                    item.hashCode(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        }
    }

    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}