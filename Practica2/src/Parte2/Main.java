package Parte2;

public class Main {
    private static final int N = 20;
    private static final int M = 20;

    public static void main(String[] args) throws InterruptedException {
        long t = System.currentTimeMillis();
        LockBakery lock = new LockBakery(2*M);
        Entero e = new Entero();
        Hilo[] hilos = new Hilo[2*M];
        for(int i = 0; i < M; ++i){
            hilos[i] = new Hilo(i, e, lock,true, N);
            hilos[M + i] = new Hilo(M+i, e, lock, false, N);
        }

        for(int i = 0; i < M; ++i){
            hilos[i].start();
            hilos[M + i].start();
        }

        for(int i = 0; i < M; ++i){
            hilos[i].join();
            hilos[M + i].join();
        }
        System.out.println(e.get());
        System.out.println(System.currentTimeMillis() - t);
    }
}
