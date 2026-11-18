# Dompetku Demo — mockup UI dompet digital (Jetpack Compose)

Aplikasi Android **100% offline** untuk latihan pemodelan UI: satu layar beranda bergaya dompet digital.
Tidak ada jaringan (manifest tanpa permission `INTERNET`), tidak ada API, tidak ada transaksi. Semua tombol hanya memunculkan snackbar "hanya mockup UI".

## Isi layar
- **Header biru**: logo generik, saldo, tombol sembunyikan/tampilkan (👁), menu cepat *Top Up / Minta / Kirim / Pesan*.
- **Banner promosi** + **grid menu layanan** (4×2) yang menimpa bagian bawah banner.
- **Card feed** informasi (4 baris), **carousel banner** (HorizontalPager), **panel proteksi** (header "N aktivitas terlindungi", kolom pencarian, tombol AMANKAN).
- **Bottom navigation**: Beranda, Aktivitas, **PAY** (tombol bulat di tengah), Dompet, Saya.

## Uji teks saldo panjang (state lokal)
- Ketuk ikon ✏️ di header, **atau tekan lama** angka saldo → dialog "Ubah nominal saldo (demo)".
- Isi angka sampai 24 digit atau pakai chip preset (*Nol, Kecil, Jutaan, Miliar, Ekstrem*). Pratinjau format `Rp 1.234.567` tampil langsung.
- Teks saldo memakai `AutoSizeText`: otomatis mengecil sampai muat satu baris (min 11sp).
- Nilai disimpan di **SharedPreferences** (`BalanceStore`) dan dibaca lagi saat aplikasi dibuka.

## Struktur
```
app/src/main/java/id/demo/dompetku/
  MainActivity.kt              edge-to-edge + tema
  data/BalanceStore.kt         SharedPreferences (saldo & visibilitas)
  util/Money.kt                format "1.234.567" (string murni, tanpa Long)
  ui/WalletViewModel.kt        state: balanceDigits, balanceVisible, selectedTab
  ui/WalletApp.kt              Scaffold, snackbar, dialog, pilih tab
  ui/HomeScreen.kt             susunan layar beranda
  ui/theme/Theme.kt            warna & MaterialTheme
  ui/components/               AutoSizeText, BalanceHeader (+QuickActions), PromoBanners,
                               ServiceGrid, FeedCard, ProtectionPanel, WalletBottomBar, BalanceEditDialog
app/src/test/.../MoneyTest.kt  unit test format angka
```

## Build
**Android Studio**: buka folder ini → Sync → Run. (Android Studio membuat gradle wrapper otomatis.)

**GitHub**: push ke `main`/`master` → workflow `.github/workflows/build.yml` menjalankan unit test dan `assembleDebug`.
APK ada di Actions → run terakhir → Artifacts (`DompetkuDemo-debug-apk`, disimpan 3 hari). Tidak perlu gradle wrapper di repo.

Versi: AGP 8.7.3 · Kotlin 2.0.21 · Compose BOM 2024.12.01 · compile/target SDK 35 · min SDK 26 · JDK 17.

## Catatan
- Merek, logo, dan nama fitur dompet sungguhan sengaja tidak dipakai; semua teks, ikon, dan banner adalah generik buatan sendiri.
- Pita kecil "MOCKUP UI • data lokal…" di header sengaja dipertahankan supaya tampilan ini tidak disalahartikan sebagai aplikasi dompet asli.
