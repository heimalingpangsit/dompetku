package id.demo.dompetku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AddCard
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.NorthEast
import androidx.compose.material.icons.outlined.SouthWest
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.theme.WalletBlue
import id.demo.dompetku.util.formatGrouped

/** Pita kecil penanda ini hanya mockup UI. */
@Composable
fun DemoRibbon(modifier: Modifier = Modifier) {
    Text(
        text = "MOCKUP UI  •  data lokal, bukan aplikasi dompet sungguhan",
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
        color = Color.White.copy(alpha = 0.75f),
        fontSize = 10.sp,
        textAlign = TextAlign.Center,
        maxLines = 1,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BalanceHeader(
    balanceDigits: String,
    visible: Boolean,
    onToggleVisible: () -> Unit,
    onEditBalance: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shown = if (visible) formatGrouped(balanceDigits) else "••••••"
    Row(
        modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 4.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LogoBadge()
        Spacer(Modifier.width(10.dp))
        Text(text = "Rp", color = Color.White, fontSize = 17.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.width(6.dp))
        AutoSizeText(
            text = shown,
            maxFontSize = 34.sp,
            modifier = Modifier
                .weight(1f, fill = false)
                .combinedClickable(onClick = onToggleVisible, onLongClick = onEditBalance),
        )
        IconButton(onClick = onToggleVisible) {
            Icon(
                imageVector = if (visible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                contentDescription = if (visible) "Sembunyikan saldo" else "Tampilkan saldo",
                tint = Color.White,
            )
        }
        IconButton(onClick = onEditBalance) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Ubah nominal saldo (demo)",
                tint = Color.White.copy(alpha = 0.8f),
            )
        }
    }
}

@Composable
private fun LogoBadge() {
    Box(
        modifier = Modifier.size(38.dp).background(Color.White, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.AccountBalanceWallet,
            contentDescription = null,
            tint = WalletBlue,
            modifier = Modifier.size(22.dp),
        )
    }
}

private data class QuickAction(val label: String, val icon: ImageVector)

private val quickActions = listOf(
    QuickAction("Top Up", Icons.Outlined.AddCard),
    QuickAction("Minta", Icons.Outlined.SouthWest),
    QuickAction("Kirim", Icons.Outlined.NorthEast),
    QuickAction("Pesan", Icons.Outlined.Mail),
)

@Composable
fun QuickActions(modifier: Modifier = Modifier, onClick: (String) -> Unit = {}) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        quickActions.forEach { action ->
            Column(
                modifier = Modifier.weight(1f).clickable { onClick(action.label) },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .border(BorderStroke(2.dp, Color.White.copy(alpha = 0.6f)), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(action.icon, contentDescription = action.label, tint = Color.White, modifier = Modifier.size(30.dp))
                }
                Spacer(Modifier.size(6.dp))
                Text(action.label, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}
