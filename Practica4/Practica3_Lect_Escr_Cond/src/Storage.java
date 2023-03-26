import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage implements Almacen {
    private int[] productos;
    private final Lock l = new ReentrantLock();
    private final Condition OKread = l.newCondition(), OKWrite = l.newCondition();
    private int cap, nw, nr;

    public Storage(int n){
        productos = new int[n];
        cap = n;
        nw = 0;
        nr = 0;
    }

    @Override
    public void escribir(int producto, int pos) {
        l.lock();
        while(nw > 0 || nr > 0){
            try {
                OKWrite.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nw++;
        l.unlock();
        productos[pos % cap] = producto;
        l.lock();
        nw--;
        OKWrite.signal();
        OKread.signalAll();
        l.unlock();
    }

    @Override
    public int leer(int pos) {
        l.lock();
        while(nw > 0){
            try {
                OKread.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nr++;
        l.unlock();
        int aux = productos[pos % cap];
        l.lock();
        nr--;
        if(nr == 0){
            OKWrite.signal();
        }
        l.unlock();
        return aux;
    }

    public int getCap(){
        return cap;
    }
}
