package id.demo.dompetku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.demo.dompetku.ui.theme.CardBorder
import id.demo.dompetku.ui.theme.TextMuted
import id.demo.dompetku.ui.theme.WalletBlue

/** Header biru "N aktivitas terlindungi" + kolom pencarian nomor mencurigakan + status proteksi. State input murni lokal. */
@Composable
fun ProtectionPanel(modifier: Modifier = Modifier, protectedCount: Int = 3, onClick: (String) -> Unit = {}) {
    var query by rememberSaveable { mutableStateOf("") }
    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(shape)
            .border(BorderStroke(1.dp, CardBorder), shape),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WalletBlue)
                .clickable { onClick("Aktivitas terlindungi") }
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Shield, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
            Spacer(Modifier.width(10.dp))
            Text(
                text = "$protectedCount aktivitas terlindungi!",
                modifier = Modifier.weight(1f),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Color.White)
        }

        Column(Modifier.fillMaxWidth().background(Color.White).padding(16.dp)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it.take(32) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = TextMuted) },
                placeholder = { Text("Ada nomor telepon mencurigakan?", color = TextMuted) },
            )
            Spacer(Modifier.size(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Lock, contentDescription = null, tint = WalletBlue, modifier = Modifier.size(34.dp))
                    Spacer(Modifier.width(6.dp))
                    Column {
                        Text("PROTEKSI", color = WalletBlue, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                        Text("AKUN", color = WalletBlue, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
                ProtectionAction(percent = 75, onClick = { onClick("Amankan") })
            }
        }
    }
}

@Composable
private fun ProtectionAction(percent: Int, onClick: () -> Unit) {
    val shape = RoundedCornerShape(10.dp)
    Row(
        modifier = Modifier
            .clip(shape)
            .border(BorderStroke(1.5.dp, WalletBlue), shape)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.background(WalletBlue).padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Shield, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text("$percent%", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Box(Modifier.padding(horizontal = 18.dp)) {
            Text("AMANKAN", color = WalletBlue, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
