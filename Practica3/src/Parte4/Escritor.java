package Parte4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Escritor extends Thread{
    private Random rand;
    private Storage st;
    private int N, nr, nw, dw;
    private Semaphore w;

    public Escritor(Storage st, int N, Semaphore w, int nr, int nw, int dw){
        this.st = st;
        this.N = N;
        this.w = w;
        this.nr = nr;
        this.nw = nw;
        this.dw = dw;
        rand = new Random();
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            if(nr > 0 || nw > 0){
                dw++;
                try {
                    w.acquire();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            nw++;
            int aux = rand.nextInt();
            st.almacenar(aux);
            w.release();
            nw--;
            if(dw > 0){
                dw--;
            }
            System.out.println("Soy el escritor " + this.getId() + " y he escrito el elemento " + aux);
        }
    }

}
