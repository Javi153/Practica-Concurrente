package Parte2;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class LockBakery extends Lock{
    private AtomicIntegerArray turn;
    private volatile int max;

    public LockBakery(int len){
        turn = new AtomicIntegerArray(len);
    }

    @Override
    public void takeLock(int ind) {
        max = 0;
    }

    @Override
    public void releaseLock(int ind) {

    }
}
