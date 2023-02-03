package android.bignerdranch.bateopontoai

import android.bignerdranch.bateopontoai.data.AlarmItem
import android.bignerdranch.bateopontoai.presentation.MainScreenViewModel
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import java.time.LocalTime

class AlarmReceiver : BroadcastReceiver() {
    var currentAlarmState = mutableStateOf(AlarmItem("", null))
        private set

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("ALARME LIGADO PAI $message no horario ${LocalTime.now().hour}: ${LocalTime.now().minute}")


        //viewModel.setCurrentAlarm()
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(context, notification)
        r.play()
        // TODO test interval
        val timer = object : CountDownTimer(50000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                r.stop()
            }
        }

        timer.start()
    }
}
