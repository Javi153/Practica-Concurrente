package Parte4;

import java.util.concurrent.Semaphore;

public class Main {
    private final static int N = 3;
    private final static int NV = 10;
    private final static int M = 3;
    private final static int MV = 10;
    private final static int SS = 20;
    public static void main(String[] args) throws InterruptedException {
        int nr = 0, nw = 0, dr = 0, dw = 0;
        Semaphore e, r, w;
        Storage st = new Storage(SS);
        e = new Semaphore(1, true);
        r = new Semaphore(0, true);
        w = new Semaphore(0, true);
        Escritor[] escr = new Escritor[N];
        Lector[] lect = new Lector[M];
        for(int i = 0; i < N; ++i){
            escr[i] = new Escritor(st, NV, w, nr, nw, dw);
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
