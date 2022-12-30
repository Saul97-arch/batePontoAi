package android.bignerdranch.bateopontoai.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class EntryState(
    val firstEntry: LocalDateTime = LocalDateTime.now(),
    val secondEntry: LocalDateTime = LocalDateTime.now(),
    val thirdEntry: LocalDateTime = LocalDateTime.now(),
    val fourthEntry: LocalDateTime = LocalDateTime.now()
)
