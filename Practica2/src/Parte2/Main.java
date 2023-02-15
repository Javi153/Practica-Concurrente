package Parte2;

public class Main {
    private static final int N = 100;
    private static final int M = 50;

    public static void main(String[] args) throws InterruptedException {
        LockRompeEmpate lock = new LockRompeEmpate(2*M);
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
        System.out.println(e.getEntero());
    }
}
