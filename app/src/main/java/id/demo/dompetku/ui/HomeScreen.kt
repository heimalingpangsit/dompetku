package id.demo.dompetku.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.components.BalanceHeader
import id.demo.dompetku.ui.components.DemoRibbon
import id.demo.dompetku.ui.components.FeedCard
import id.demo.dompetku.ui.components.HeroBanner
import id.demo.dompetku.ui.components.ProtectionPanel
import id.demo.dompetku.ui.components.PromoCarousel
import id.demo.dompetku.ui.components.QuickActions
import id.demo.dompetku.ui.components.ServiceGrid
import id.demo.dompetku.ui.theme.TextMuted
import id.demo.dompetku.ui.theme.WalletBlue

/** Tinggi bagian bawah banner yang tertutup kartu menu layanan. */
private val BannerOverlap = 36.dp
private val BannerHeight = 170.dp

@Composable
fun HomeContent(
    state: WalletUiState,
    onToggleVisible: () -> Unit,
    onEditBalance: () -> Unit,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        // 1) header biru: saldo + toggle + menu cepat
        Column(Modifier.fillMaxWidth().background(WalletBlue).statusBarsPadding()) {
            DemoRibbon()
            BalanceHeader(
                balanceDigits = state.balanceDigits,
                visible = state.balanceVisible,
                onToggleVisible = onToggleVisible,
                onEditBalance = onEditBalance,
            )
            QuickActions(onClick = onItemClick)
        }

        // 2) banner promosi + kartu grid layanan yang menimpa bagian bawah banner
        Box(Modifier.fillMaxWidth()) {
            HeroBanner(height = BannerHeight)
            ServiceGrid(modifier = Modifier.padding(top = BannerHeight - BannerOverlap), onClick = onItemClick)
        }

        Spacer(Modifier.height(12.dp))
        FeedCard(onClick = { onItemClick("Feed") })
        Spacer(Modifier.height(12.dp))
        PromoCarousel()
        Spacer(Modifier.height(12.dp))
        ProtectionPanel(onClick = onItemClick)
        Spacer(Modifier.height(28.dp))
    }
}

/** Halaman placeholder untuk tab selain Beranda (tetap mockup). */
@Composable
fun PlaceholderTab(title: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize().statusBarsPadding(), contentAlignment = Alignment.Center) {
        Text("$title\n(mockup, belum ada isi)", color = TextMuted, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}
