package android.bignerdranch.bateopontoai.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class MainScreenViewModel : ViewModel() {

    // Implement Mask
    // change keyboard to numeric
    // alarms


    // set the properties of entryState, when the properties change, the alarm hour will be changed
    // for instance, when entry1 property will be changed, toggle the alarm for the right hour
    val entryState = mutableStateOf(EntryState())
    val entry1 = mutableStateOf("")
    var out1 = mutableStateOf("")
    val entry2 = mutableStateOf("")
    val out2 = mutableStateOf("")

    // entry 1 marked, so an alarm is defined for out1
    // with the value of out1, entry2 will be defined based with the value of LUNCH_TIME

    // Invalid hours
    // discover more cases
    // hours like 9:99
    // 25:55 should go to 2:55
    // 99:99 should go to 9:9 or 9:09
    // if min greater or equal than 60 truncate and just show first number


    fun onEntry1Changed(value: String) {
        this.entry1.value = value

        if (value.length == 3) {
            // AM values
            val hours = this.entry1.value[0].digitToInt()
            val minutes = this.entry1.value.slice(1..2).toInt()

            val entry1ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry1ToLocalTime.plusHours(3)
            setOut1Value(entry2NewValue.toString())
        } else if (value.length == 4) {
            // PM values
            // merge value[0] and value[1] to 1 number
            val hours = this.entry1.value.slice(0..1).toInt()
            val minutes = this.entry1.value.slice(2..3).toInt()
            val entry1ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry1ToLocalTime.plusHours(3)
            setOut1Value(entry2NewValue.toString())
        }

        if (this.entry1.value.isEmpty() || this.entry1.value.isBlank()) {
            this.out1.value = ""
            this.entry2.value = ""
            this.out2.value = ""
        }

//        if (this.entry1.value[0] == '0') {
//            this.entry1.value = ""
//        }
    }

    private fun setOut1Value(value: String) {
        this.out1.value = value
        setEntry2Value(value)
    }

    private fun setEntry2Value(value: String) {

        if (value.length == 4) {
            // AM values
            val hours = value[0].digitToInt()
            val minutes = if (value.slice(2..3)[0] == ':') {
                0
            } else if (value.slice(2..3)[0] == '0') {
                value[3].digitToInt()
            } else {
                value.slice(2..3).toInt()
            }
            val entry2ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry2ToLocalTime.plusHours(LUNCH_TIME)
            this.entry2.value = entry2NewValue.toString()
            setOut2Value(this.entry2.value)
        } else if (value.length == 5) {
            // PM values
            // merge value[0] and value[1] to 1 number
            val hours = value.slice(0..1).toInt()
            val minutes = value.slice(3..4).toInt()
            val entry1ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry1ToLocalTime.plusHours(LUNCH_TIME)
            this.entry2.value = entry2NewValue.toString()
            setOut2Value(this.entry2.value)
        }
    }

    private fun setOut2Value(value: String) {
        if (value.length == 4) {
            // AM values
            val hours = value[0].digitToInt()
            val minutes = if (value.slice(2..3)[0] == ':') {
                0
            } else if (value.slice(2..3)[0] == '0') {
                value[3].digitToInt()
            } else {
                value.slice(2..3).toInt()
            }
            val entry2ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry2ToLocalTime.plusHours(5)
            this.out2.value = entry2NewValue.toString()
        } else if (value.length == 5) {
            // PM values
            // merge value[0] and value[1] to 1 number
            val hours = value.slice(0..1).toInt()
            val minutes = value.slice(3..4).toInt()
            val entry1ToLocalTime = LocalTime.of(hours, minutes)
            val entry2NewValue = entry1ToLocalTime.plusHours(5)
            this.out2.value = entry2NewValue.toString()
        }
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
        private const val LUNCH_TIME = 1L
    }
}
