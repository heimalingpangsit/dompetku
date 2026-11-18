package id.demo.dompetku.ui.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Satu baris teks yang otomatis mengecil sampai muat lebarnya (dipakai untuk teks saldo panjang).
 * Teks baru digambar setelah ukuran final ketemu supaya tidak "berkedip".
 */
@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    maxFontSize: TextUnit = 36.sp,
    minFontSize: TextUnit = 11.sp,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    var fontSize by remember(text, maxFontSize) { mutableStateOf(maxFontSize) }
    var ready by remember(text, maxFontSize) { mutableStateOf(false) }

    BasicText(
        text = text,
        modifier = modifier.drawWithContent { if (ready) drawContent() },
        style = TextStyle(color = color, fontSize = fontSize, fontWeight = fontWeight),
        maxLines = 1,
        softWrap = false,
        overflow = TextOverflow.Clip,
        onTextLayout = { result ->
            if (result.didOverflowWidth && fontSize > minFontSize) {
                fontSize = (fontSize.value * 0.92f).sp
            } else {
                ready = true
            }
        },
    )
}
