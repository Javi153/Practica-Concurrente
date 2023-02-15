package Parte1;

public class Hilo extends Thread{
    private Entero e;
    private Lock1 lock;
    private boolean inc;
    private int N;

    public Hilo(Entero e, Lock1 lock, boolean inc, int N){
       this.e = e;
       this.lock = lock;
       this.inc = inc;
       this.N = N;
    }

    public void run(){
        if (inc) {
            for(int i = 0; i < N; ++i){
                lock.takeLock(inc);
                e.incrementar();
                lock.releaseLock(inc);
            }
        }
        else{
            for(int i = 0; i < N; ++i){
                lock.takeLock(inc);
                e.decrementar();
                lock.releaseLock(inc);
            }
        }
    }
}
