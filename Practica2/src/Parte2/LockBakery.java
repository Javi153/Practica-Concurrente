package Parte2;

public class LockBakery extends Lock {
    private Entero turn[];

    public LockBakery(int len) {
        turn = new Entero[len];
        for (int i = 0; i < len; ++i) {
            turn[i] = new Entero();
        }
    }

    @Override
    public void takeLock(int ind) {
        int max = 0;
        turn[ind].set(1);
        for (int i = 0; i < turn.length; ++i) {
            max = Math.max(max, turn[i].get());
        }
        turn[ind].set(max + 1);
        for (int i = 0; i < turn.length; ++i) {
            if (i != ind) {
                while (turn[i].get() != 0 && (turn[ind].get() > turn[i].get() || (turn[ind].get() == turn[i].get() && ind > i)))
                    ;
            }
        }
    }

    @Override
    public void releaseLock(int ind) {
        turn[ind].set(0);
    }
}
