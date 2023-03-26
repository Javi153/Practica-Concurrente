import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class miMonitorCond {
    private final Lock l = new ReentrantLock();
    private volatile int e;

    public miMonitorCond(){
        e = 0;
    }

    public void op(boolean inc){
        l.lock();
        if(inc){
            e++;
        }
        else{
            e--;
        }
        l.unlock();
    }

    public int get(){
        return e;
    }
}
