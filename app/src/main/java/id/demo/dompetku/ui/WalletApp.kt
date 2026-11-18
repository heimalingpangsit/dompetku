package id.demo.dompetku.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import id.demo.dompetku.ui.components.BalanceEditDialog
import id.demo.dompetku.ui.components.TAB_ACTIVITY
import id.demo.dompetku.ui.components.TAB_HOME
import id.demo.dompetku.ui.components.TAB_ME
import id.demo.dompetku.ui.components.TAB_WALLET
import id.demo.dompetku.ui.components.WalletBottomBar
import id.demo.dompetku.ui.theme.ScreenBg
import kotlinx.coroutines.launch

@Composable
fun WalletApp(viewModel: WalletViewModel = viewModel()) {
    val state = viewModel.uiState
    var showEditDialog by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val onItemClick: (String) -> Unit = { label ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar("$label — hanya mockup UI")
        }
    }

    Scaffold(
        containerColor = ScreenBg,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            WalletBottomBar(
                selected = state.selectedTab,
                onSelect = viewModel::selectTab,
                onPay = { onItemClick("PAY") },
            )
        },
    ) { inner: PaddingValues ->
        val content = Modifier.padding(inner)
        when (state.selectedTab) {
            TAB_HOME -> HomeContent(
                state = state,
                onToggleVisible = viewModel::toggleVisibility,
                onEditBalance = { showEditDialog = true },
                onItemClick = onItemClick,
                modifier = content,
            )
            TAB_ACTIVITY -> PlaceholderTab("Aktivitas", content)
            TAB_WALLET -> PlaceholderTab("Dompet", content)
            TAB_ME -> PlaceholderTab("Saya", content)
            else -> PlaceholderTab("-", content)
        }
    }

    if (showEditDialog) {
        BalanceEditDialog(
            currentDigits = state.balanceDigits,
            onDismiss = { showEditDialog = false },
            onSave = { digits ->
                viewModel.setBalance(digits)
                showEditDialog = false
            },
        )
    }
}
