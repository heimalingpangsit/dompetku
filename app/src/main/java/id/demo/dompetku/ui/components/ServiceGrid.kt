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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.CreditScore
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.Stars
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.theme.CardBorder
import id.demo.dompetku.ui.theme.TextPrimary

private data class Service(val label: String, val icon: ImageVector, val tint: Color)

private val services = listOf(
    Service("Promo Spesial", Icons.Outlined.LocalOffer, Color(0xFFFF8A1F)),
    Service("Hadiah Seru", Icons.Outlined.CardGiftcard, Color(0xFFE8590C)),
    Service("Member VIP", Icons.Outlined.WorkspacePremium, Color(0xFF1B86EF)),
    Service("Cicilan", Icons.Outlined.CreditScore, Color(0xFF1B86EF)),
    Service("Game Zone", Icons.Outlined.SportsEsports, Color(0xFF12B886)),
    Service("Pulsa & Data", Icons.Outlined.PhoneAndroid, Color(0xFFE03131)),
    Service("Poin Reward", Icons.Outlined.Stars, Color(0xFFF59F00)),
    Service("Lihat Semua", Icons.Outlined.Apps, Color(0xFF495057)),
)

/** Kartu putih berisi menu layanan 4 kolom x 2 baris. */
@Composable
fun ServiceGrid(modifier: Modifier = Modifier, onClick: (String) -> Unit = {}) {
    Card(
        modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, CardBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(Modifier.padding(vertical = 10.dp)) {
            services.chunked(4).forEach { row ->
                Row(Modifier.fillMaxWidth()) {
                    row.forEach { service ->
                        ServiceCell(service, Modifier.weight(1f)) { onClick(service.label) }
                    }
                    repeat(4 - row.size) { Spacer(Modifier.weight(1f)) }
                }
            }
        }
    }
}

@Composable
private fun ServiceCell(service: Service, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.clickable(onClick = onClick).padding(horizontal = 4.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.size(46.dp).background(service.tint.copy(alpha = 0.12f), RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(service.icon, contentDescription = service.label, tint = service.tint, modifier = Modifier.size(28.dp))
        }
        Spacer(Modifier.size(6.dp))
        Text(
            text = service.label,
            color = TextPrimary,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2,
        )
    }
}
