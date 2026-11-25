import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private Rental rental;
    private Scanner scanner;

    public Main() {
        this.rental = new Rental();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.jalankan();
    }

    public void jalankan() {
        int pilihan = -1;
        while (pilihan != 0) {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");
            prosesPilihan(pilihan);
        }
        scanner.close();
        System.out.println("Program selesai. Terima kasih!");
    }

    private void tampilkanMenu() {
        System.out.println("\n====================================");
        System.out.println("       SISTEM RENTAL KENDARAAN      ");
        System.out.println("====================================");
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Tambah Penyewa");
        System.out.println("3. Lihat Daftar Kendaraan");
        System.out.println("4. Buat Penyewaan");
        System.out.println("5. Akhiri Penyewaan");
        System.out.println("6. Lihat Antrean Penyewaan");
        System.out.println("7. Proses Antrean");
        System.out.println("8. Tampilkan Laporan Rental");
        System.out.println("0. Keluar");
        System.out.println("====================================");
    }

    private void prosesPilihan(int pilihan) {
        switch (pilihan) {
            case 1:
                tambahKendaraan();
                break;
            case 2:
                tambahPenyewa();
                break;
            case 3:
                lihatDaftarKendaraan();
                break;
            case 4:
                buatPenyewaan();
                break;
            case 5:
                akhiriPenyewaan();
                break;
            case 6:
                lihatAntrean();
                break;
            case 7:
                prosesAntrean();
                break;
            case 8:
                rental.tampilkanLaporan();
                break;
            case 0:
                break;
            default:
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
    }

    private void tambahKendaraan() {
        System.out.println("\n--- Tambah Kendaraan ---");
        String id = bacaString("ID Kendaraan: ");
        String nopol = bacaString("Nomor Polisi: ");
        String merk = bacaString("Merk: ");
        String tipe = bacaString("Tipe: ");
        int tahun = bacaInt("Tahun: ");
        double tarif = bacaDouble("Tarif Per Hari: ");

        Kendaraan kendaraan = new Kendaraan(id, nopol, merk, tipe, tahun, tarif);
        if (rental.tambahKendaraan(kendaraan)) {
            System.out.println("Kendaraan berhasil ditambahkan!");
        } else {
            System.out.println("Gagal menambahkan kendaraan. Cek ID/Nomor Polisi duplikat atau data tidak valid.");
        }
    }

    private void tambahPenyewa() {
        System.out.println("\n--- Tambah Penyewa ---");
        String id = bacaString("ID Penyewa: ");
        String nama = bacaString("Nama: ");
        String identitas = bacaString("Nomor Identitas: ");
        String telepon = bacaString("Nomor Telepon: ");
        String alamat = bacaString("Alamat: ");

        Penyewa penyewa = new Penyewa(id, nama, identitas, telepon, alamat);
        if (rental.tambahPenyewa(penyewa)) {
            System.out.println("Penyewa berhasil ditambahkan!");
        } else {
            System.out.println("Gagal menambahkan penyewa. Cek ID duplikat atau data tidak valid.");
        }
    }

    private void lihatDaftarKendaraan() {
        System.out.println("\n--- Daftar Kendaraan ---");
        var daftar = rental.getDaftarKendaraan();
        if (daftar.isEmpty()) {
            System.out.println("Belum ada kendaraan terdaftar.");
            return;
        }
        for (Kendaraan k : daftar) {
            k.tampilkanInfo();
            System.out.println();
        }
    }

    private void buatPenyewaan() {
        System.out.println("\n--- Buat Penyewaan ---");
        String idSewa = bacaString("ID Sewa: ");
        String idKendaraan = bacaString("ID Kendaraan: ");
        String idPenyewa = bacaString("ID Penyewa: ");
        LocalDate tanggalMulai = bacaTanggal("Tanggal Mulai (yyyy-MM-dd): ");
        int lamaHari = bacaInt("Lama Hari: ");

        Kendaraan kendaraan = rental.cariKendaraan(idKendaraan);
        if (kendaraan == null) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }

        Penyewa penyewa = null;
        for (Penyewa p : rental.getDaftarPenyewa()) {
            if (p.getIdPenyewa().equals(idPenyewa)) {
                penyewa = p;
                break;
            }
        }
        if (penyewa == null) {
            System.out.println("Penyewa tidak ditemukan.");
            return;
        }

        Sewa sewa = new Sewa(idSewa, kendaraan, penyewa, tanggalMulai, lamaHari);
        if (rental.buatSewa(sewa)) {
            System.out.println("Penyewaan berhasil dibuat!");
            sewa.tampilkanInfo();
        } else {
            System.out.println("Gagal membuat penyewaan. Kendaraan mungkin sedang disewa atau data tidak valid.");
        }
    }

    private void akhiriPenyewaan() {
        System.out.println("\n--- Akhiri Penyewaan ---");
        String idSewa = bacaString("ID Sewa: ");
        if (rental.akhiriSewa(idSewa)) {
            System.out.println("Penyewaan berhasil diakhiri. Kendaraan kembali tersedia.");
        } else {
            System.out.println("Gagal mengakhiri penyewaan. ID tidak ditemukan atau sudah selesai.");
        }
    }

    private void lihatAntrean() {
        rental.getAntreanSewa().tampilkanAntrean();
    }

    private void prosesAntrean() {
        System.out.println("\n--- Proses Antrean ---");
        Sewa sewa = rental.prosesAntrean();
        if (sewa == null) {
            System.out.println("Antrean kosong.");
        } else if (sewa.isStatusAktif()) {
            System.out.println("Transaksi dari antrean berhasil diproses:");
            sewa.tampilkanInfo();
        } else {
            System.out.println("Transaksi diambil dari antrean tapi kendaraan tidak tersedia:");
            sewa.tampilkanInfo();
        }
    }

    private String bacaString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
    }

    private double bacaDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
    }

    private LocalDate bacaTanggal(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah. Gunakan yyyy-MM-dd (contoh: 2024-12-25).");
            }
        }
    }
}