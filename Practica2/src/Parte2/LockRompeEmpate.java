package Parte2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class LockRompeEmpate extends Lock {
    private Entero in[], last[];

    public LockRompeEmpate(int n){
        in = new Entero[n + 1];
        last = new Entero[n + 1];
    }

    @Override
    public void takeLock(int ind) {
        for(int i = 1; i < in.length; ++i){
            in[ind].set(i);
            last[i].set(ind);
            for(int j = 1; j < in.length; ++j){
                while(in[j].get() >= in[ind].get() && last[i].get() == ind);
            }
        }
    }

    @Override
    public void releaseLock(int ind) {
        in[ind].set(0);
    }
}
