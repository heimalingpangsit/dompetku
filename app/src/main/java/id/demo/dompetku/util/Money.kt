package id.demo.dompetku.util

/** Batas digit saldo demo. Cukup panjang untuk menguji teks ekstrem tanpa perlu tipe Long. */
const val MAX_DIGITS = 24

/** Hanya sisakan angka 0-9 dan potong sampai [maxLen] digit. */
fun sanitizeDigits(input: String, maxLen: Int = MAX_DIGITS): String =
    input.filter { it in '0'..'9' }.take(maxLen)

/** "1234567" -> "1.234.567". Nol di depan dibuang; kosong dianggap "0". Murni string, tanpa parsing angka. */
fun formatGrouped(digits: String): String {
    val clean = digits.trimStart('0').ifEmpty { "0" }
    return clean.reversed().chunked(3).joinToString(".").reversed()
}

fun formatRupiah(digits: String): String = "Rp ${formatGrouped(digits)}"
