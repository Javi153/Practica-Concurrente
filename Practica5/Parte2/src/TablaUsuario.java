import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TablaUsuario {
    private HashMap<String, Usuario> tabla;
    private Lock l = new ReentrantLock();
    private final Condition OKread = l.newCondition(), OKWrite = l.newCondition();
    private int nw, nr;

    public TablaUsuario(){
        tabla = new HashMap<>();
        nw = 0;
        nr = 0;
    }

    public void write(String s, Usuario t, boolean remove){
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
        tabla.put(s, t);
        l.lock();
        nw--;
        OKWrite.signal();
        OKread.signalAll();
        l.unlock();
    }

    public void write(String s, Usuario t){
        write(s, t, false);
    }

    public void remove(String s){
        write(s, null, true);
    }

    public Usuario read(String s) {
        l.lock();
        while (nw > 0) {
            try {
                OKread.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nr++;
        l.unlock();
        Usuario aux = new Usuario(tabla.get(s));
        l.lock();
        nr--;
        if (nr == 0) {
            OKWrite.signal();
        }
        l.unlock();
        return aux;
    }

    public HashSet<Usuario> valores(){
        l.lock();
        while (nw > 0) {
            try {
                OKread.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nr++;
        l.unlock();
        HashSet<Usuario> aux = new HashSet<>();
        for(Usuario u : tabla.values()){
            aux.add(new Usuario(u));
        }
        l.lock();
        nr--;
        if (nr == 0) {
            OKWrite.signal();
        }
        l.unlock();
        return aux;
    }

    public HashSet<String> claves(){
        l.lock();
        while (nw > 0) {
            try {
                OKread.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        nr++;
        l.unlock();
        HashSet<String> aux = new HashSet<>();
        for(String s : tabla.keySet()){
            aux.add(new String(s));
        }
        l.lock();
        nr--;
        if (nr == 0) {
            OKWrite.signal();
        }
        l.unlock();
        return aux;
    }
}
