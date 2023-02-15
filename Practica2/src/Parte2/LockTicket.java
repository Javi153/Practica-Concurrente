package Parte2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class LockTicket extends Lock{
    private volatile int number, next;
    private AtomicIntegerArray turn;

    public LockTicket(int len){
        number = 1;
        next = 1;
        turn = new AtomicIntegerArray(len);
    }

    @Override
    public void takeLock(int ind) {
        turn.getAndAdd(number, 1);
        while(turn.get(ind) != next);
    }

    @Override
    public void releaseLock(int ind) {
        next++;
    }
}
