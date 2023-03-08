package Parte1;

import java.util.concurrent.Semaphore;

public class Hilo extends Thread{
    private Entero e;
    private Semaphore sem;
    private boolean inc;
    private int N;

    public Hilo(Entero e, Semaphore sem, boolean inc, int N){
        this.e = e;
        this.sem = sem;
        this.inc = inc;
        this.N = N;
    }

    public void run(){
        if (inc) {
            for(int i = 0; i < N; ++i){
                try {
                    sem.acquire();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                e.incrementar();
                sem.release();
            }
        }
        else{
            for(int i = 0; i < N; ++i){
                try {
                    sem.acquire();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                e.decrementar();
                sem.release();
            }
        }
    }
}
