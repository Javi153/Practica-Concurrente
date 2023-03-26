import java.util.concurrent.Semaphore;

public class Main {
    private final static int N = 3;
    private final static int NV = 10;
    private final static int M = 3;
    private final static int MV = 10;
    private final static int SS = 20;
    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 100; ++j) {
            Storage st = new Storage(SS);
            Escritor[] escr = new Escritor[N];
            Lector[] lect = new Lector[M];
            for (int i = 0; i < N; ++i) {
                escr[i] = new Escritor(st, NV);
                escr[i].start();
            }
            for (int i = 0; i < M; ++i) {
                lect[i] = new Lector(st, MV);
                lect[i].start();
            }
            for (int i = 0; i < N; ++i) {
                escr[i].join();
            }
            for (int i = 0; i < M; ++i) {
                lect[i].join();
            }
        }
    }
}
