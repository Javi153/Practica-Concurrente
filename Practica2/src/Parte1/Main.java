package Parte1;

public class Main {
    private static final int N = 10000;
    private static final int M = 1;

    public static void main(String[] args) throws InterruptedException {
        Entero turno1 = new Entero();
        Entero turno2 = new Entero();
        Lock1 lock = new Lock1(turno1, turno2);
        Entero e = new Entero();
        Hilo[] hilos = new Hilo[2*M];
        for(int i = 0; i < M; ++i){
            hilos[i] = new Hilo(e, lock,true, N);
            hilos[M + i] = new Hilo(e, lock, false, N);
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
