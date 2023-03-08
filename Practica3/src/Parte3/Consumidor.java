package Parte3;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread{
    private Storage st;
    private int N;
    private Semaphore empty, full, mutexP, mutexC;

    public Consumidor(Storage st, int N, Semaphore empty, Semaphore full, Semaphore mutexP, Semaphore mutexC){
        this.st = st;
        this.N = N;
        this.empty = empty;
        this.full = full;
        this.mutexP = mutexP;
        this.mutexC = mutexC;
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            try {
                full.acquire();
                mutexC.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int aux = st.extraer();
            mutexP.release();
            empty.release();
            System.out.println("Soy el consumidor " + this.getId() + " y he consumido el objeto " + aux);
        }
    }
}
