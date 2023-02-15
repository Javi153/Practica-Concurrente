package Parte2;

public class Hilo extends Thread{
    private int id;
    private Entero e;
    private Lock lock;
    private boolean inc;
    private int N;

    public Hilo(int id, Entero e, Lock lock, boolean inc, int N){
        this.id = id;
        this.e = e;
        this.lock = lock;
        this.inc = inc;
        this.N = N;
    }

    public void run(){
        if (inc) {
            for(int i = 0; i < N; ++i){
                lock.takeLock(id);
                e.incrementar();
                lock.releaseLock(id);
            }
        }
        else{
            for(int i = 0; i < N; ++i){
                lock.takeLock(id);
                e.decrementar();
                lock.releaseLock(id);
            }
        }
    }
}
