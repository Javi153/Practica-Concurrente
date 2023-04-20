import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Puertos implements Serializable {
    private final static int M = 16384;
    private final boolean puertos[] = new boolean[M];

    private Random r;

    public Puertos(){
        Arrays.fill(puertos, false);
        Random r = new Random();
    }

    public int getPort(){
        int i = r.nextInt(M);
        while(puertos[i]){
            i = r.nextInt(M);
        }
        puertos[i] = true;
        return i + 49152;
    }

}
