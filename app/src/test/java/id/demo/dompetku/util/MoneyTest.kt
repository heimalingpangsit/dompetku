package id.demo.dompetku.util

import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyTest {
    @Test
    fun groupsThousandsWithDots() {
        assertEquals("Rp 1.234.567", formatRupiah("1234567"))
        assertEquals("Rp 999", formatRupiah("999"))
        assertEquals("Rp 1.000", formatRupiah("1000"))
    }

    @Test
    fun emptyOrZerosBecomeZero() {
        assertEquals("Rp 0", formatRupiah(""))
        assertEquals("Rp 0", formatRupiah("000"))
    }

    @Test
    fun handlesVeryLongNumbersWithoutOverflow() {
        assertEquals("Rp 123.456.789.012.345.678", formatRupiah("123456789012345678"))
    }

    @Test
    fun sanitizeKeepsOnlyDigitsAndLimitsLength() {
        assertEquals("123", sanitizeDigits("a1b2-c3 "))
        assertEquals(MAX_DIGITS, sanitizeDigits("9".repeat(100)).length)
    }
}
