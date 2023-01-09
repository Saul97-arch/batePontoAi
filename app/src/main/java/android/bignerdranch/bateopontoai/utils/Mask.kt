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

            if (offset <= 4) return offset

            return 5
        }

        override fun transformedToOriginal(offset: Int): Int {
            // 9:12
            // 12:22
            // TODO DEBUG TO DISCOVER WHAT IS OFFSSET
            if (offset <=5) return offset
            return 4
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
