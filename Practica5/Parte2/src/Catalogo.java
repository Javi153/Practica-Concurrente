import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Catalogo {
    private Map<String, Pelicula> catalogo;
    private int nw, nr, dw, dr;
    private Semaphore e,r,w;

    public Catalogo() {
        catalogo = new HashMap<>();
        nw = 0;
        nr = 0;
        dw = 0;
        dr = 0;
        e = new Semaphore(1, true);
        r = new Semaphore(0, true);
        w = new Semaphore(0, true);
    }

    public void write(Pelicula p) {
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
        catalogo.put(p.getName(), p);
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

    public Pelicula read(String s) {
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
        Pelicula aux = new Pelicula(catalogo.get(s));
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
}
