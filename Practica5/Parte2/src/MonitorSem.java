import java.util.concurrent.Semaphore;

public class MonitorSem implements Monitor{

    protected int nw, nr, dw, dr;
    protected Semaphore e,r,w;

    public MonitorSem(){
        nw = 0;
        nr = 0;
        dw = 0;
        dr = 0;
        e = new Semaphore(1, true);
        r = new Semaphore(0, true);
        w = new Semaphore(0, true);
    }
    @Override
    public void requestRead() {
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
    }

    @Override
    public void requestWrite() {
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
    }

    @Override
    public void releaseRead() {
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
    }

    @Override
    public void releaseWrite() {
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
}
