package android.bignerdranch.bateopontoai.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class EntryState(
    var firstEntry: LocalDateTime? = null,
    var secondEntry: LocalDateTime? = null,
    var thirdEntry: LocalDateTime? = null,
    var fourthEntry: LocalDateTime? = null
)
