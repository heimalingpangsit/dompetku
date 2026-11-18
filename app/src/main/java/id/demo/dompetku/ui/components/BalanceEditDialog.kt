package id.demo.dompetku.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.demo.dompetku.util.MAX_DIGITS
import id.demo.dompetku.util.formatRupiah
import id.demo.dompetku.util.sanitizeDigits

private val presets = listOf(
    "Nol" to "0",
    "Kecil" to "25750",
    "Jutaan" to "1500000",
    "Miliar" to "12500000000",
    "Ekstrem" to "123456789012345678",
)

/** Dialog sederhana untuk mengubah teks saldo secara manual (khusus uji tampilan teks panjang). */
@Composable
fun BalanceEditDialog(
    currentDigits: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
) {
    var input by remember { mutableStateOf(if (currentDigits == "0") "" else currentDigits) }
    val digits = sanitizeDigits(input)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Ubah nominal saldo (demo)") },
        text = {
            Column {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = sanitizeDigits(it) },
                    label = { Text("Nominal (angka saja)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    supportingText = { Text("${digits.length}/$MAX_DIGITS digit") },
                )
                Spacer(Modifier.height(8.dp))
                Text("Pratinjau: ${formatRupiah(digits)}", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    presets.forEach { (label, value) ->
                        AssistChip(onClick = { input = value }, label = { Text(label) })
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = { onSave(digits) }) { Text("Simpan") } },
        dismissButton = {
            Row {
                TextButton(onClick = { input = "" }) { Text("Kosongkan") }
                TextButton(onClick = onDismiss) { Text("Batal") }
            }
        },
    )
}
