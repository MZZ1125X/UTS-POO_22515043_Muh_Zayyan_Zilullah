public class Penyewa {
    private String idPenyewa;
    private String nama;
    private String nomorIdentitas;
    private String nomorTelepon;
    private String alamat;

    public Penyewa() {
        this.idPenyewa = "";
        this.nama = "";
        this.nomorIdentitas = "";
        this.nomorTelepon = "";
        this.alamat = "";
    }

    public Penyewa(String id, String nama, String identitas, String telepon, String alamat) {
        this.idPenyewa = id;
        this.nama = nama;
        this.nomorIdentitas = identitas;
        this.nomorTelepon = telepon;
        this.alamat = alamat;
    }

    public boolean validasiData() {
        if (idPenyewa == null || idPenyewa.trim().isEmpty()) {
            return false;
        }
        if (nama == null || nama.trim().isEmpty()) {
            return false;
        }
        if (nomorIdentitas == null || nomorIdentitas.trim().isEmpty()) {
            return false;
        }
        if (nomorTelepon == null || nomorTelepon.trim().isEmpty()) {
            return false;
        }
        if (alamat == null || alamat.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public void ubahDataKontak(String telepon, String alamat) {
        this.nomorTelepon = telepon;
        this.alamat = alamat;
    }

    public void tampilkanInfo() {
        System.out.println("=== Informasi Penyewa ===");
        System.out.println("ID Penyewa      : " + idPenyewa);
        System.out.println("Nama            : " + nama);
        System.out.println("Nomor Identitas : " + nomorIdentitas);
        System.out.println("Nomor Telepon   : " + nomorTelepon);
        System.out.println("Alamat          : " + alamat);
        System.out.println("=========================");
    }

    public String getIdPenyewa() {
        return idPenyewa;
    }

    public void setIdPenyewa(String idPenyewa) {
        this.idPenyewa = idPenyewa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorIdentitas() {
        return nomorIdentitas;
    }

    public void setNomorIdentitas(String nomorIdentitas) {
        this.nomorIdentitas = nomorIdentitas;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}