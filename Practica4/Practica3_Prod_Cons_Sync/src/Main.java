public class Main {
    private final static int N = 10;
    private final static int NV = 10;
    private final static int M = 10;
    private final static int MV = 10;
    private final static int SS = 20;
    public static void main(String[] args) throws InterruptedException {
        Storage st = new Storage(SS);
        Productor[] prod = new Productor[N];
        Consumidor[] cons = new Consumidor[M];
        for(int i = 0; i < N; ++i){
            prod[i] = new Productor(st, NV);
            prod[i].start();
        }
        for(int i = 0; i < M; ++i){
            cons[i] = new Consumidor(st, MV);
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
