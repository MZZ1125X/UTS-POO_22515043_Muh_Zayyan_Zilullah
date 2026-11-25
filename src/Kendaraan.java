import java.text.NumberFormat;
import java.util.Locale;

public class Kendaraan {
    private String idKendaraan;
    private String nomorPolisi;
    private String merk;
    private String tipe;
    private int tahun;
    private double tarifPerHari;
    private boolean statusDisewa;
    
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public Kendaraan() {
        this.idKendaraan = "";
        this.nomorPolisi = "";
        this.merk = "";
        this.tipe = "";
        this.tahun = 0;
        this.tarifPerHari = 0.0;
        this.statusDisewa = false;
    }

    public Kendaraan(String id, String nopol, String merk, String tipe, int tahun, double tarif) {
        this.idKendaraan = id;
        this.nomorPolisi = nopol;
        this.merk = merk;
        this.tipe = tipe;
        this.tahun = tahun;
        this.tarifPerHari = tarif;
        this.statusDisewa = false;
    }

    public boolean validasiData() {
        if (idKendaraan == null || idKendaraan.trim().isEmpty()) {
            return false;
        }
        if (nomorPolisi == null || nomorPolisi.trim().isEmpty()) {
            return false;
        }
        if (merk == null || merk.trim().isEmpty()) {
            return false;
        }
        if (tipe == null || tipe.trim().isEmpty()) {
            return false;
        }
        if (tahun <= 0) {
            return false;
        }
        if (tarifPerHari <= 0) {
            return false;
        }
        return true;
    }

    public boolean cekKetersediaan() {
        return !statusDisewa;
    }

    public void ubahStatusDisewa(boolean status) {
        this.statusDisewa = status;
    }

    public void tampilkanInfo() {
        System.out.println("=== Informasi Kendaraan ===");
        System.out.println("ID Kendaraan   : " + idKendaraan);
        System.out.println("Nomor Polisi   : " + nomorPolisi);
        System.out.println("Merk           : " + merk);
        System.out.println("Tipe           : " + tipe);
        System.out.println("Tahun          : " + tahun);
        System.out.println("Tarif Per Hari : " + CURRENCY_FORMAT.format(tarifPerHari));
        System.out.println("Status Disewa  : " + (statusDisewa ? "Ya" : "Tidak"));
        System.out.println("============================");
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public void setNomorPolisi(String nomorPolisi) {
        this.nomorPolisi = nomorPolisi;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public double getTarifPerHari() {
        return tarifPerHari;
    }

    public void setTarifPerHari(double tarifPerHari) {
        this.tarifPerHari = tarifPerHari;
    }

    public boolean isStatusDisewa() {
        return statusDisewa;
    }
}