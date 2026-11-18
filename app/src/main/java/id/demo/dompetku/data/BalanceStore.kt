package id.demo.dompetku.data

import android.content.Context

/**
 * Penyimpanan LOKAL (SharedPreferences) untuk nilai demo. Tidak ada jaringan, API, atau transaksi sungguhan.
 * Saldo disimpan sebagai string angka supaya bisa diuji dengan nominal sangat panjang.
 */
class BalanceStore(context: Context) {
    private val prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    var balanceDigits: String
        get() = prefs.getString(KEY_BALANCE, "0") ?: "0"
        set(value) = prefs.edit().putString(KEY_BALANCE, value).apply()

    var balanceVisible: Boolean
        get() = prefs.getBoolean(KEY_VISIBLE, true)
        set(value) = prefs.edit().putBoolean(KEY_VISIBLE, value).apply()

    private companion object {
        const val PREFS = "wallet_demo_prefs"
        const val KEY_BALANCE = "balance_digits"
        const val KEY_VISIBLE = "balance_visible"
    }
}
