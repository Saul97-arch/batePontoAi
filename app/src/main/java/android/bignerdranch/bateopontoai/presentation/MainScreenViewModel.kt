package android.bignerdranch.bateopontoai.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.O)
class MainScreenViewModel : ViewModel() {

    var state by mutableStateOf(EntryState())


}
