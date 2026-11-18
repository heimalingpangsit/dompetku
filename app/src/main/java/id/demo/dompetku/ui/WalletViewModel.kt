package id.demo.dompetku.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import id.demo.dompetku.data.BalanceStore
import id.demo.dompetku.util.sanitizeDigits

data class WalletUiState(
    val balanceDigits: String = "0",
    val balanceVisible: Boolean = true,
    val selectedTab: Int = 0,
)

/** State lokal murni: dibaca dari / ditulis ke SharedPreferences, tanpa repository jaringan. */
class WalletViewModel(app: Application) : AndroidViewModel(app) {
    private val store = BalanceStore(app)

    var uiState by mutableStateOf(
        WalletUiState(
            balanceDigits = store.balanceDigits,
            balanceVisible = store.balanceVisible,
        ),
    )
        private set

    fun toggleVisibility() {
        val next = !uiState.balanceVisible
        store.balanceVisible = next
        uiState = uiState.copy(balanceVisible = next)
    }

    /** Dipanggil dari dialog "Ubah nominal". Input apa pun dibersihkan jadi angka. */
    fun setBalance(raw: String) {
        val digits = sanitizeDigits(raw).trimStart('0').ifEmpty { "0" }
        store.balanceDigits = digits
        uiState = uiState.copy(balanceDigits = digits)
    }

    fun selectTab(index: Int) {
        uiState = uiState.copy(selectedTab = index)
    }
}
