public class Main {
    private final static int N = 3;
    private final static int NV = 10;
    private final static int M = 3;
    private final static int MV = 10;
    private final static int SS = 20;
    public static void main(String[] args) throws InterruptedException {
        int nr = 0, nw = 0, dr = 0, dw = 0;
        Storage st = new Storage(SS);
        Escritor[] escr = new Escritor[N];
        Lector[] lect = new Lector[M];
        for(int i = 0; i < N; ++i){
            escr[i] = new Escritor(st, NV, e, r, w, nr, nw, dr, dw);
            escr[i].start();
        }
        for(int i = 0; i < M; ++i){
            lect[i] = new Lector(st, MV, e, r, w, nr, nw, dr, dw);
            lect[i].start();
        }
        for(int i = 0; i < N; ++i){
            escr[i].join();
        }
        for(int i = 0; i < M; ++i){
            lect[i].join();
        }
    }
}