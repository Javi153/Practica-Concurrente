package Parte4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Lector extends Thread{
    private Storage st;
    private Integer N, nr, nw, dr, dw;
    private Semaphore e, r, w;
    private Random rand;

    public Lector(Storage st, int N, Semaphore e, Semaphore r, Semaphore w, Integer nr, Integer nw, Integer dr, Integer dw){
        this.st = st;
        this.N = N;
        this.e = e;
        this.r = r;
        this.w = w;
        this.nr = nr;
        this.nw = nw;
        this.dr = dr;
        this.dw = dw;
        rand = new Random();
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
            int aux = st.leer(rand.nextInt(0,st.getCap()));
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
