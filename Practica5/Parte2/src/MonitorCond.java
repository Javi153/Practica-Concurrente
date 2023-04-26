import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorCond implements Monitor{
    protected Lock l = new ReentrantLock();
    protected final Condition OKread = l.newCondition(), OKWrite = l.newCondition();
    protected int nw, nr;

    public MonitorCond(){
        nw = 0;
        nr = 0;
    }
    @Override
    public void requestRead() {
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
    }

    @Override
    public void requestWrite() {
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
    }

    @Override
    public void releaseRead() {
        l.lock();
        nr--;
        if (nr == 0) {
            OKWrite.signal();
        }
        l.unlock();
    }

    @Override
    public void releaseWrite() {
        l.lock();
        nw--;
        OKWrite.signal();
        OKread.signalAll();
        l.unlock();
    }
}
