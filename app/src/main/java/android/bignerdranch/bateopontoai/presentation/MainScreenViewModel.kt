package android.bignerdranch.bateopontoai.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class MainScreenViewModel : ViewModel() {
    // set the properties of entryState, when the properties change, the alarm hour will be changed
    // for instance, when entry1 property will be changed, toggle the alarm for the right hour
    val entryState = mutableStateOf(EntryState())
    val entry1 = mutableStateOf("")
    var out1 = mutableStateOf("")
    val entry2 = mutableStateOf("")
    val out2 = mutableStateOf("")

    // entry 1 marked, so an alarm is defined for out1
    // with the value of out1, entry2 will be defined based with the value of LUNCH_TIME

    fun onEntry1Changed(value: String) {
        this.entry1.value = value
        // ask for lunch hour
        // ask for lunch time, then entry 2 will be already defined
        // transform string "9:00" in number in order to add
        // then tranform again
        // now i can get and add

        if (value.length == 4) {
            // AM values
            val hours = this.entry1.value[0].digitToInt()
            val minutes = if (this.entry1.value.slice(2..3)[0] == ':') {
                0
            } else if (this.entry1.value.slice(2..3)[0] == '0') {
                this.entry1.value[3].digitToInt()
            } else {
                this.entry1.value.slice(2..3).toInt()
            }
            val entry1ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry1ToLocalTime.plusHours(3)
            onOut1Changed(entry2NewValue.toString())
        } else if (value.length == 5) {
            // PM values
            // merge value[0] and value[1] to 1 number
            val hours = this.entry1.value.slice(0..1).toInt()
            val minutes = this.entry1.value.slice(3..4).toInt()
            val entry1ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry1ToLocalTime.plusHours(3)
            onOut1Changed(entry2NewValue.toString())
        }

        if (this.entry1.value.isEmpty() || this.entry1.value.isBlank()) {
            this.out1.value = ""
        }


    }

    private fun onOut1Changed(value: String) {
        this.out1.value = value
    }


    // get current time and set
    // set time for entry 1, 2, 3 and 4

    // begin with 3 hours for the first half and 5 for the second
    // later on give options to customize

    // idea for button +
    // fills the current input with the current hour

    // for now focus on when input 1 filled
    // input 2 is automatic filled with the correct hour
    // I.E : First entry is 9 am, the second input will be 12 am
    // the alarm will be set to 11:59

    companion object {
        private const val LUNCH_TIME = 1
    }
}
