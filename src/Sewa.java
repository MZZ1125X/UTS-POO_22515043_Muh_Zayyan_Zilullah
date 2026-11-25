import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;

public class Sewa {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    private String idSewa;
    private LocalDate tanggalMulai;
    private int lamaHari;
    private LocalDate tanggalKembali;
    private double tarifPerHari;
    private double totalBiaya;
    private boolean statusAktif;
    private Kendaraan kendaraan;
    private Penyewa penyewa;

    public Sewa() {
        this.idSewa = "";
        this.tanggalMulai = LocalDate.now();
        this.lamaHari = 0;
        this.tanggalKembali = LocalDate.now();
        this.tarifPerHari = 0.0;
        this.totalBiaya = 0.0;
        this.statusAktif = false;
        this.kendaraan = null;
        this.penyewa = null;
    }

    public Sewa(String id, Kendaraan kendaraan, Penyewa penyewa, LocalDate tanggalMulai, int lamaHari) {
        this.idSewa = id;
        this.kendaraan = kendaraan;
        this.penyewa = penyewa;
        this.tanggalMulai = tanggalMulai;
        this.lamaHari = lamaHari;
        this.tarifPerHari = kendaraan.getTarifPerHari();
        this.totalBiaya = hitungTotalBiaya();
        this.tanggalKembali = hitungTanggalKembali();
        this.statusAktif = true;
    }

    public double hitungTotalBiaya() {
        return tarifPerHari * lamaHari;
    }

    public LocalDate hitungTanggalKembali() {
        return tanggalMulai.plusDays(lamaHari);
    }

    public void ubahStatus(boolean status) {
        this.statusAktif = status;
    }

    public void tampilkanInfo() {
        System.out.println("=== Informasi Transaksi Sewa ===");
        System.out.println("ID Sewa         : " + idSewa);
        if (kendaraan != null) {
            System.out.println("Kendaraan       : " + kendaraan.getMerk() + " " + kendaraan.getTipe() + " (" + kendaraan.getNomorPolisi() + ")");
        }
        if (penyewa != null) {
            System.out.println("Penyewa         : " + penyewa.getNama() + " (" + penyewa.getIdPenyewa() + ")");
        }
        System.out.println("Tanggal Mulai   : " + tanggalMulai);
        System.out.println("Tanggal Kembali : " + tanggalKembali);
        System.out.println("Lama Hari       : " + lamaHari);
        System.out.println("Tarif Per Hari  : " + CURRENCY_FORMAT.format(tarifPerHari));
        System.out.println("Total Biaya     : " + CURRENCY_FORMAT.format(totalBiaya));
        System.out.println("Status Aktif    : " + (statusAktif ? "Ya" : "Tidak"));
        System.out.println("=================================");
    }

    public String getIdSewa() {
        return idSewa;
    }

    public void setIdSewa(String idSewa) {
        this.idSewa = idSewa;
    }

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(LocalDate tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
        this.tanggalKembali = hitungTanggalKembali();
    }

    public int getLamaHari() {
        return lamaHari;
    }

    public void setLamaHari(int lamaHari) {
        this.lamaHari = lamaHari;
        this.totalBiaya = hitungTotalBiaya();
        this.tanggalKembali = hitungTanggalKembali();
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public double getTarifPerHari() {
        return tarifPerHari;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public boolean isStatusAktif() {
        return statusAktif;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
        this.tarifPerHari = kendaraan.getTarifPerHari();
        this.totalBiaya = hitungTotalBiaya();
    }

    public Penyewa getPenyewa() {
        return penyewa;
    }

    public void setPenyewa(Penyewa penyewa) {
        this.penyewa = penyewa;
    }
}