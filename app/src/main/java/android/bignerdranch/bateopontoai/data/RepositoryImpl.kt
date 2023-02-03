package android.bignerdranch.bateopontoai.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDateTime

class RepositoryImpl : Repository {
    override val currentAlarmState: MutableState<AlarmItem> =
        mutableStateOf(AlarmItem("", LocalDateTime.now()))

    companion object {
        fun instance(): Repository {
            return RepositoryImpl()
        }
    }
}
