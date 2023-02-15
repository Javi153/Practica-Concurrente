package Ej2;

public class Main2 {
    private static final int N = 10;
    private static final int M = 10;
    public static void main(String[] args) throws InterruptedException {
        Entero e = new Entero();
        Hilo2[] hilos = new Hilo2[2*M];
        for(int i = 0; i < M; ++i){
            hilos[i] = new Hilo2(e, true, N);
            hilos[M + i] = new Hilo2(e, false, N);
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
