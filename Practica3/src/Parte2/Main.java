package Parte2;

import java.util.concurrent.Semaphore;

public class Main {
    private final static int N = 10;
    private final static int NV = 10;
    private final static int M = 10;
    private final static int MV = 10;
    public static void main(String[] args) throws InterruptedException {
        Semaphore empty, full;
        Storage st = new Storage();
        empty = new Semaphore(1, true);
        full = new Semaphore(0, true);
        Productor[] prod = new Productor[N];
        Consumidor[] cons = new Consumidor[M];
        for(int i = 0; i < N; ++i){
            prod[i] = new Productor(st, NV, empty, full);
            prod[i].start();
        }
        for(int i = 0; i < M; ++i){
            cons[i] = new Consumidor(st, MV, empty, full);
            cons[i].start();
        }
        for(int i = 0; i < N; ++i){
            prod[i].join();
        }
        for(int i = 0; i < M; ++i){
            cons[i].join();
        }
    }
}
