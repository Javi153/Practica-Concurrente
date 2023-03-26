import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage implements Almacen {
    private int[] productos;
    private final Lock l = new ReentrantLock(true);
    private final Condition condAlm = l.newCondition(), condExtr = l.newCondition();
    private int ini, fin;
    private volatile int cap;

    public Storage(int n){
        productos = new int[n];
        ini = 0;
        fin = 0;
        cap = 0;
    }
    @Override
    public void almacenar(int producto) throws InterruptedException {
        l.lock();
        while(cap == productos.length){
            condAlm.await();
        }
        productos[fin] = producto;
        fin = (fin + 1) % productos.length;
        cap++;
        condExtr.signal();
        l.unlock();
    }

    @Override
    public int extraer() throws InterruptedException {
        l.lock();
        while(cap == 0){
            condExtr.await();
        }
        int res = productos[ini];
        ini = (ini + 1) % productos.length;
        cap--;
        condAlm.signal();
        l.unlock();
        return res;
    }
}

