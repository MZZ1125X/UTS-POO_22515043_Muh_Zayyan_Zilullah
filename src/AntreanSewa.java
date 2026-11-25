public class AntreanSewa {
    private Sewa[] data;
    private int front;
    private int rear;
    private int jumlah;
    private int kapasitas;

    public AntreanSewa(int kapasitas) {
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas harus lebih besar dari 0");
        }
        this.kapasitas = kapasitas;
        this.data = new Sewa[kapasitas];
        this.front = -1;
        this.rear = -1;
        this.jumlah = 0;
    }

    public boolean enqueue(Sewa sewa) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear = (rear + 1) % kapasitas;
        data[rear] = sewa;
        jumlah++;
        return true;
    }

    public Sewa dequeue() {
        if (isEmpty()) {
            return null;
        }
        Sewa sewa = data[front];
        data[front] = null;
        front = (front + 1) % kapasitas;
        jumlah--;
        if (isEmpty()) {
            front = -1;
            rear = -1;
        }
        return sewa;
    }

    public Sewa front() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }

    public boolean isEmpty() {
        return jumlah == 0;
    }

    public boolean isFull() {
        return jumlah == kapasitas;
    }

    public int size() {
        return jumlah;
    }

    public void tampilkanAntrean() {
        if (isEmpty()) {
            System.out.println("Antrean belum memiliki transaksi");
            return;
        }
        System.out.println("=== Daftar Antrean Sewa ===");
        for (int i = 0; i < jumlah; i++) {
            int indeks = (front + i) % kapasitas;
            System.out.println((i + 1) + ". " + data[indeks].getIdSewa() + " - " + data[indeks].getPenyewa().getNama());
        }
        System.out.println("============================");
    }
}