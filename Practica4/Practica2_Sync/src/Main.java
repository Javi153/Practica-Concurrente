public class Main {
    private static final int N = 20;
    private static final int M = 20;

    public static void main(String[] args) throws InterruptedException {
        long t = System.currentTimeMillis();
        miMonitorSync mon = new miMonitorSync();
        miHilo[] hilos = new miHilo[2*M];
        for(int i = 0; i < M; ++i){
            hilos[i] = new miHilo(i, mon,true, N);
            hilos[M + i] = new miHilo(M+i, mon, false, N);
        }

        for(int i = 0; i < M; ++i){
            hilos[i].start();
            hilos[M + i].start();
        }

        for(int i = 0; i < M; ++i){
            hilos[i].join();
            hilos[M + i].join();
        }
        System.out.println(mon.get());
        System.out.println(System.currentTimeMillis() - t);
    }
}
