import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Puertos implements Serializable {
    private final static int M = 16384;
    private final static int OFFSET = 49152;
    private final boolean[] puertos = new boolean[M];
    private final Random r;

    public Puertos(){
        Arrays.fill(puertos, false);
        r = new Random();
    }

    public synchronized int getPort(){
        int i = r.nextInt(M);
        while(puertos[i]){
            i = r.nextInt(M);
        }
        puertos[i] = true;
        return i + OFFSET;
    }

    public synchronized void closePort(int i){
        i -= OFFSET;
        puertos[i] = false;
    }

}
