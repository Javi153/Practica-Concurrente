package Parte4;

import java.util.concurrent.Semaphore;

public class Storage implements Almacen {
    private int[] productos;
    private int cap, nw, nr, dw, dr;
    private Semaphore e,r,w;

    public Storage(int n){
        productos = new int[n];
        cap = n;
        nw = 0;
        nr = 0;
        dw = 0;
        dr = 0;
        e = new Semaphore(1, true);
        r = new Semaphore(0, true);
        w = new Semaphore(0, true);
    }
    @Override
    public void escribir(int producto, int pos) {
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
        productos[pos % cap] = producto;
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

    }

    @Override
    public int leer(int pos) {
        try {
            e.acquire();
            if(nw > 0 || dw > 0){
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
        int aux = productos[pos % cap];
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
        return aux;
    }

    public int getCap(){
        return cap;
    }
}
