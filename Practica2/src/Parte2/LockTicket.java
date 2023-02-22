package Parte2;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket extends Lock{
    private volatile int next;
    AtomicInteger number;
    private Entero turn[];

    public LockTicket(int len){
        number = new AtomicInteger(1);
        next = 1;
        turn = new Entero[len];
        for(int i = 0; i < len; ++i){
            turn[i] = new Entero();
        }
    }

    @Override
    public void takeLock(int ind) {
        turn[ind].set(number.getAndAdd(1));
        while(turn[ind].get() != next);
    }

    @Override
    public void releaseLock(int ind) {
        next++;
    }
}
