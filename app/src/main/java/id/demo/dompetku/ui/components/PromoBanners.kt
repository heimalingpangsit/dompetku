package id.demo.dompetku.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.matchParentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.theme.Gold
import id.demo.dompetku.ui.theme.Orange
import id.demo.dompetku.ui.theme.TextPrimary
import id.demo.dompetku.ui.theme.WalletBlue
import id.demo.dompetku.ui.theme.WalletBlueSoft

/** Banner besar di bawah menu cepat. Bagian bawahnya (44dp) sengaja kosong karena tertutup kartu menu layanan. */
@Composable
fun HeroBanner(modifier: Modifier = Modifier, height: androidx.compose.ui.unit.Dp = 170.dp) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(Brush.horizontalGradient(listOf(WalletBlue, WalletBlueSoft, Color(0xFF86C8FF)))),
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(Color.White.copy(alpha = 0.09f), radius = size.height * 0.85f, center = Offset(size.width * 0.95f, size.height * 0.05f))
            drawCircle(Color.White.copy(alpha = 0.06f), radius = size.height * 0.55f, center = Offset(size.width * 0.05f, size.height * 0.95f))
            drawCircle(Gold.copy(alpha = 0.35f), radius = size.height * 0.16f, center = Offset(size.width * 0.80f, size.height * 0.30f))
        }
        Row(
            modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 46.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Brush.verticalGradient(listOf(Gold, Orange)))
                    .padding(horizontal = 16.dp, vertical = 10.dp),
            ) {
                Column {
                    Text("PROMO", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("12.12", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
            Spacer(Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Serba Hemat", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
                Text("& Banyak Hadiah", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color.White)
                        .padding(horizontal = 26.dp, vertical = 6.dp),
                ) {
                    Text("SERBU", color = WalletBlue, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
        }
    }
}

private data class Promo(val title: String, val subtitle: String, val colors: List<Color>)

private val promos = listOf(
    Promo("Diamond Game Murah", "Top up game favorit mulai Rp 11", listOf(Color(0xFF1B86EF), Color(0xFF3B5BDB))),
    Promo("Cashback Belanja", "Belanja online lebih hemat", listOf(Color(0xFFFF8A1F), Color(0xFFE8590C))),
    Promo("Kumpulkan Poin", "Tukar poin dengan hadiah menarik", listOf(Color(0xFF12B886), Color(0xFF0B7285))),
)

@Composable
fun PromoCarousel(modifier: Modifier = Modifier) {
    val pager = rememberPagerState(pageCount = { promos.size })
    HorizontalPager(
        state = pager,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 28.dp),
        pageSpacing = 12.dp,
    ) { page ->
        PromoCard(promos[page])
    }
}

@Composable
private fun PromoCard(promo: Promo) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Brush.linearGradient(promo.colors)),
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawCircle(Color.White.copy(alpha = 0.10f), radius = size.height * 0.7f, center = Offset(size.width * 0.92f, size.height * 0.15f))
            drawCircle(Gold.copy(alpha = 0.30f), radius = size.height * 0.2f, center = Offset(size.width * 0.78f, size.height * 0.75f))
        }
        Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Center) {
            Text(promo.title, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(6.dp))
            Text(promo.subtitle, color = Color.White.copy(alpha = 0.92f), fontSize = 14.sp)
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .padding(horizontal = 18.dp, vertical = 6.dp),
            ) {
                Text("Lihat promo", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
