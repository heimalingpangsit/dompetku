package id.demo.dompetku.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val WalletBlue = Color(0xFF1B86EF)
val WalletBlueDeep = Color(0xFF0B63C9)
val WalletBlueSoft = Color(0xFF58A9F6)
val ScreenBg = Color(0xFFF3F6FA)
val CardBorder = Color(0xFFE2E8F0)
val TextPrimary = Color(0xFF1E2A36)
val TextMuted = Color(0xFF8793A1)
val Gold = Color(0xFFFFC63D)
val Orange = Color(0xFFFF8A1F)

private val Colors = lightColorScheme(
    primary = WalletBlue,
    onPrimary = Color.White,
    background = ScreenBg,
    surface = Color.White,
    onSurface = TextPrimary,
)

@Composable
fun DompetkuTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, content = content)
}
