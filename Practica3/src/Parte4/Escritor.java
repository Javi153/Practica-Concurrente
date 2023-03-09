package Parte4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Escritor extends Thread{
    private Random rand;
    private Storage st;
    private int N, nr, nw, dr, dw;
    private Semaphore e, r, w;

    public Escritor(Storage st, int N, Semaphore e, Semaphore r,Semaphore w, int nr, int nw, int dr, int dw){
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
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            if(nr > 0 || nw > 0){
                dw++;
                try {
                    e.acquire();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                w.release();
            }
            nw++;
            e.release();
            int aux = rand.nextInt();
            st.almacenar(aux);
            try {
                e.acquire();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            nw--;
            if(dw > 0){
                dw--;
                w.release();
            }
            else if(dr > 0){
                dr--;
                r.release();
            }
            else{
                e.release();
            }
            System.out.println("Soy el escritor " + this.getId() + " y he escrito el elemento " + aux);
        }
    }

}
