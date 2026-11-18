package id.demo.dompetku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.theme.CardBorder
import id.demo.dompetku.ui.theme.TextPrimary
import id.demo.dompetku.ui.theme.WalletBlue
import id.demo.dompetku.ui.theme.WalletBlueSoft

private val feeds = listOf(
    "masuk untuk lihat update terbaru!",
    "hai \uD83D\uDC4B, siap menjelajah feed?",
    "berikan \uD83D\uDCAC,\u2764\uFE0F,\uD83C\uDF81 biar seru!",
    "sambungkan koneksi yang terpercaya!",
)

@Composable
fun FeedCard(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, CardBorder),
    ) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 8.dp)) {
            feeds.forEach { text ->
                Row(
                    modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.size(36.dp).background(WalletBlueSoft, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("Feed") }
                            append(" ")
                            append(text)
                        },
                        modifier = Modifier.weight(1f),
                        color = TextPrimary,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.Campaign, contentDescription = null, tint = WalletBlue, modifier = Modifier.size(22.dp))
                }
            }
        }
    }
}
