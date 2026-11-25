import java.util.ArrayList;

public class Rental {
    private String idRental;
    private String namaRental;
    private String alamat;
    private String nomorTelepon;
    private ArrayList<Kendaraan> daftarKendaraan;
    private ArrayList<Penyewa> daftarPenyewa;
    private ArrayList<Sewa> daftarSewa;
    private AntreanSewa antreanSewa;

    public Rental() {
        this.daftarKendaraan = new ArrayList<>();
        this.daftarPenyewa = new ArrayList<>();
        this.daftarSewa = new ArrayList<>();
        this.antreanSewa = new AntreanSewa(10);
    }

    public boolean tambahKendaraan(Kendaraan kendaraan) {
        if (kendaraan == null || !kendaraan.validasiData()) {
            return false;
        }
        for (Kendaraan k : daftarKendaraan) {
            if (k.getIdKendaraan().equals(kendaraan.getIdKendaraan())) {
                return false;
            }
            if (k.getNomorPolisi().equals(kendaraan.getNomorPolisi())) {
                return false;
            }
        }
        daftarKendaraan.add(kendaraan);
        return true;
    }

    public boolean tambahPenyewa(Penyewa penyewa) {
        if (penyewa == null || !penyewa.validasiData()) {
            return false;
        }
        for (Penyewa p : daftarPenyewa) {
            if (p.getIdPenyewa().equals(penyewa.getIdPenyewa())) {
                return false;
            }
        }
        daftarPenyewa.add(penyewa);
        return true;
    }

    public Kendaraan cariKendaraan(String id) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getIdKendaraan().equals(id)) {
                return k;
            }
        }
        return null;
    }

    public boolean buatSewa(Sewa sewa) {
        if (sewa == null || sewa.getKendaraan() == null || sewa.getPenyewa() == null) {
            return false;
        }
        Kendaraan k = cariKendaraan(sewa.getKendaraan().getIdKendaraan());
        if (k == null) {
            return false;
        }
        Penyewa p = null;
        for (Penyewa penyewa : daftarPenyewa) {
            if (penyewa.getIdPenyewa().equals(sewa.getPenyewa().getIdPenyewa())) {
                p = penyewa;
                break;
            }
        }
        if (p == null) {
            return false;
        }
        if (!k.cekKetersediaan()) {
            return false;
        }
        if (sewa.getLamaHari() <= 0) {
            return false;
        }
        k.ubahStatusDisewa(true);
        daftarSewa.add(sewa);
        return true;
    }

    public boolean akhiriSewa(String idSewa) {
        for (Sewa s : daftarSewa) {
            if (s.getIdSewa().equals(idSewa)) {
                if (!s.isStatusAktif()) {
                    return false;
                }
                s.ubahStatus(false);
                if (s.getKendaraan() != null) {
                    s.getKendaraan().ubahStatusDisewa(false);
                }
                return true;
            }
        }
        return false;
    }

    public Sewa prosesAntrean() {
        if (antreanSewa.isEmpty()) {
            return null;
        }
        Sewa sewa = antreanSewa.dequeue();
        if (sewa == null) {
            return null;
        }
        Kendaraan k = sewa.getKendaraan();
        if (k != null && k.cekKetersediaan()) {
            buatSewa(sewa);
        }
        return sewa;
    }

    public void tampilkanLaporan() {
        int totalKendaraan = daftarKendaraan.size();
        int tersedia = 0;
        int disewa = 0;
        for (Kendaraan k : daftarKendaraan) {
            if (k.cekKetersediaan()) {
                tersedia++;
            } else {
                disewa++;
            }
        }
        int totalPenyewa = daftarPenyewa.size();
        int totalSewa = daftarSewa.size();
        int aktif = 0;
        int selesai = 0;
        for (Sewa s : daftarSewa) {
            if (s.isStatusAktif()) {
                aktif++;
            } else {
                selesai++;
            }
        }
        int antrean = antreanSewa.size();

        System.out.println("=== Laporan Rental ===");
        System.out.println("Total Kendaraan    : " + totalKendaraan);
        System.out.println("  Tersedia         : " + tersedia);
        System.out.println("  Disewa           : " + disewa);
        System.out.println("Total Penyewa      : " + totalPenyewa);
        System.out.println("Total Transaksi    : " + totalSewa);
        System.out.println("  Aktif            : " + aktif);
        System.out.println("  Selesai          : " + selesai);
        System.out.println("Antrean Transaksi  : " + antrean);
        System.out.println("======================");
    }

    public String getIdRental() {
        return idRental;
    }

    public void setIdRental(String idRental) {
        this.idRental = idRental;
    }

    public String getNamaRental() {
        return namaRental;
    }

    public void setNamaRental(String namaRental) {
        this.namaRental = namaRental;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public ArrayList<Kendaraan> getDaftarKendaraan() {
        return daftarKendaraan;
    }

    public ArrayList<Penyewa> getDaftarPenyewa() {
        return daftarPenyewa;
    }

    public ArrayList<Sewa> getDaftarSewa() {
        return daftarSewa;
    }

    public AntreanSewa getAntreanSewa() {
        return antreanSewa;
    }
}