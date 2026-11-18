package id.demo.dompetku

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.demo.dompetku.ui.WalletApp
import id.demo.dompetku.ui.theme.DompetkuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            // ikon status bar putih di atas header biru, ikon navigasi gelap di atas bottom bar putih
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
        )
        setContent {
            DompetkuTheme {
                WalletApp()
            }
        }
    }
}
