package android.bignerdranch.bateopontoai

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.time.LocalTime

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("ALARME LIGADO PAI $message no horario ${LocalTime.now().hour}: ${LocalTime.now().minute}")
    }
}
