package android.bignerdranch.bateopontoai.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.bignerdranch.bateopontoai.AlarmReceiver
import android.bignerdranch.bateopontoai.AlarmScheduler
import android.content.Context
import android.content.Intent
import java.time.LocalDateTime
import java.util.*

class AndroidAlarmScheduler(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(item: AlarmItem) {

        item.localDateTime?.let {
            if (item.localDateTime < LocalDateTime.now()) return
        }

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", item.alarmName)
        }

        val calendar = getCalendarInstance(item)

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

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

    private fun getCalendarInstance(item: AlarmItem): Calendar {
        val calendar = Calendar.getInstance()
        item.localDateTime?.hour?.let { calendar.set(Calendar.HOUR_OF_DAY, it) }
        item.localDateTime?.minute?.let { calendar.set(Calendar.MINUTE, it) }
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }
}
