package Parte4;

import java.util.concurrent.Semaphore;

public class Lector extends Thread{
    private Storage st;
    private int N, nr, nw, dr, dw;
    private Semaphore e, r, w;

    public Lector(Storage st, int N, Semaphore e, Semaphore r, Semaphore w, int nr, int nw, int dr, int dw){
        this.st = st;
        this.N = N;
        this.e = e;
        this.r = r;
        this.w = w;
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            try {
                e.acquire();
            if(nw > 0){
                dr++;
                e.release();
                r.acquire();
            }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            nr++;
            if(dr > 0){
                dr--;
                r.release();
            }
            else{
                e.release();
            }
            int aux = st.extraer();
            try {
                e.acquire();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            nr--;
            if(nr == 0 && dw > 0){
                dw--;
                w.release();
            }
            else{
                e.release();
            }
            System.out.println("Soy el lector " + this.getId() + " y he leido el objeto " + aux);
        }
    }
}
