package Parte3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Productor extends Thread{
    private Random r;
    private Storage st;
    private int N;
    private Semaphore empty, full, mutexP, mutexC;

    public Productor(Storage st, int N, Semaphore empty, Semaphore full, Semaphore mutexP, Semaphore mutexC){
        this.st = st;
        this.N = N;
        this.empty = empty;
        this.full = full;
        this.mutexP = mutexP;
        this.mutexC = mutexC;
        r = new Random();
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            try {
                empty.acquire();
                mutexP.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int aux = r.nextInt();
            st.almacenar(aux);
            mutexC.release();
            full.release();
            System.out.println("Soy el productor " + this.getId() + " y he producido el elemento " + aux);
        }
    }

}
