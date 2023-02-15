package Parte2;

public class LockBakery extends Lock{
    private Entero turn[];
    private volatile int max;

    public LockBakery(int len){
        turn = new Entero[len];
    }

    @Override
    public void takeLock(int ind) {
        max = 0;
    }

    @Override
    public void releaseLock(int ind) {

    }
}
