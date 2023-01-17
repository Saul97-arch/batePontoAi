package android.bignerdranch.bateopontoai.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaskTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }
}

// TODO, fuck mask, go to time picker
fun maskFilter(text: AnnotatedString): TransformedText {

    // NNNNN-NNN
    // 9:12
    // 12:12
    val trimmed = if (text.text.length >= 3 && text.text[0].digitToInt() != 1) {
        text.text.substring(0..2)
    } else if (text.text.length >= 4 && text.text[0].digitToInt() == 1) {
        text.text.substring(0..3)
    } else {
        text.text
    }
    var out = ""

    if (text.text.length >= 3 && text.text[0].digitToInt() != 1) {
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (trimmed.length == 3 && i == 0) out += ":"
        }
    } else {
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (trimmed.length == 4 && i == 1) out += ":"
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            // a.b.c which has length 4 <- tranfsformed
            // abc which has length 2 <-- original
            // so original to transformed is offset * 2

            // 1212, 912
            // 12:12
            // 9:12

            // 912 -> 9:12
            // 1112 -> 11:12

            if (offset <= 1) return offset
            if (offset <= 3 && out.first().digitToInt() < 10) return offset + 1
            if (offset <= 4 && out.first().digitToInt() == 1) return offset + 1

            return offset
        }

        override fun transformedToOriginal(offset: Int): Int {

            return offset
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

// 03/04/1998