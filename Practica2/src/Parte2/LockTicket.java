package Parte2;
public class LockTicket extends Lock{
    private volatile int number, next;
    private Entero turn[];

    public LockTicket(int len){
        number = 1;
        next = 1;
        turn = new Entero[len];
        for(int i = 0; i < len; ++i){
            turn[i] = new Entero();
        }
    }

    @Override
    public void takeLock(int ind) {
        turn[ind].set(number);
        number++;
        while(turn[ind].get() != next);
    }

    @Override
    public void releaseLock(int ind) {
        next++;
    }
}
