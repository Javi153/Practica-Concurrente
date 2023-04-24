import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TablaFlujo {
    private HashMap<String, Flujo> tabla;
    private Lock l = new ReentrantLock();
    private final Condition OKread = l.newCondition(), OKWrite = l.newCondition();
    private int nw, nr;

    public TablaFlujo(){ //Tabla de flujos de usuario implementada como monitor con Lock y Condcional
        tabla = new HashMap<>();
        nw = 0;
        nr = 0;
    }

    public void write(String s, Flujo t, boolean remove){ //Implementamos el write para que sirva tanto para añadir flujo como para borrarlo
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
        if(!remove) {
            tabla.put(s, t);
        }
        else{
            tabla.remove(s);
        }
        l.lock();
        nw--;
        OKWrite.signal();
        OKread.signalAll();
        l.unlock();
    }

    public void write(String s, Flujo t){
        write(s, t, false);
    } //Si no se especifica se entiende que se añade

    public void remove(String s){
        write(s, null, true);
    } //Añadimos facilidad al usuario renombrando el write/remove:true como remove

    public Flujo read(String s) {
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
        Flujo f = tabla.get(s);
        l.lock();
        nr--;
        if (nr == 0) {
            OKWrite.signal();
        }
        l.unlock();
        return f;
    }
}
