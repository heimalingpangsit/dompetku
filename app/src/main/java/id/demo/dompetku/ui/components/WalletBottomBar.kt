package id.demo.dompetku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.theme.TextMuted
import id.demo.dompetku.ui.theme.TextPrimary
import id.demo.dompetku.ui.theme.WalletBlue

const val TAB_HOME = 0
const val TAB_ACTIVITY = 1
const val TAB_WALLET = 3
const val TAB_ME = 4

/**
 * Bottom navigation 5 slot: Beranda, Aktivitas, [PAY di tengah], Dompet, Saya.
 * Slot tengah tidak bisa "dipilih"; tombol PAY hanya memanggil [onPay].
 */
@Composable
fun WalletBottomBar(selected: Int, onSelect: (Int) -> Unit, onPay: () -> Unit, modifier: Modifier = Modifier) {
    val payOverflow = 34.dp
    Box(modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier.fillMaxWidth().padding(top = payOverflow).align(Alignment.BottomCenter),
            color = Color.White,
            shadowElevation = 14.dp,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().navigationBarsPadding().height(66.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NavItem("Beranda", Icons.Filled.Home, selected == TAB_HOME) { onSelect(TAB_HOME) }
                NavItem("Aktivitas", Icons.Outlined.Receipt, selected == TAB_ACTIVITY) { onSelect(TAB_ACTIVITY) }
                Spacer(Modifier.weight(1f))
                NavItem("Dompet", Icons.Outlined.AccountBalanceWallet, selected == TAB_WALLET) { onSelect(TAB_WALLET) }
                NavItem("Saya", Icons.Outlined.AccountCircle, selected == TAB_ME) { onSelect(TAB_ME) }
            }
        }
        PayButton(onClick = onPay, modifier = Modifier.align(Alignment.TopCenter))
    }
}

@Composable
private fun RowScope.NavItem(label: String, icon: ImageVector, selected: Boolean, onClick: () -> Unit) {
    val tint = if (selected) TextPrimary else TextMuted
    Column(
        modifier = Modifier.weight(1f).fillMaxHeight().clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {
        Icon(icon, contentDescription = label, tint = tint, modifier = Modifier.size(28.dp))
        Spacer(Modifier.height(3.dp))
        Text(
            text = label,
            color = tint,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1,
        )
    }
}

@Composable
private fun PayButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .size(80.dp)
            .shadow(10.dp, CircleShape)
            .clip(CircleShape)
            .background(WalletBlue)
            .border(BorderStroke(5.dp, Color(0xFFD6EAFD)), CircleShape)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {
        Icon(Icons.Filled.QrCodeScanner, contentDescription = "Bayar", tint = Color.White, modifier = Modifier.size(30.dp))
        Text("PAY", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
    }
}
