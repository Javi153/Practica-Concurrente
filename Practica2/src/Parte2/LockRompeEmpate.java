package Parte2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class LockRompeEmpate extends Lock {
    private Entero in[], last[];

    public LockRompeEmpate(int n){
        in = new Entero[n];
        last = new Entero[n];
        for(int i = 0; i < n; ++i){
            in[i] = new Entero();
            last[i] = new Entero();
        }
    }

    @Override
    public void takeLock(int ind) {
        for(int i = 0; i < in.length; ++i){
            in[ind].set(i + 1);
            last[i].set(ind + 1);
            for(int j = 0; j < in.length; ++j){
                while(in[j].get() >= in[ind].get() && last[i].get() == ind + 1);
            }
        }
    }

    @Override
    public void releaseLock(int ind) {
        in[ind].set(0);
    }
}
