import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class Puertos implements Serializable { //Clase de puertos disponibles para conexiones p2p
    private final static int M = 16384; //Cantidad de puertos disponibles (lo recomendado en internet)
    private final static int OFFSET = 49152; //Primer puerto a usar (lo recomendado en internet)
    private final boolean[] puertos = new boolean[M]; //Array de booleanos para marcar los puertos disponibles, false es libre, true ocupado
    private final Random r; //Elegimos el puerto al azar pues no sabemos cuanto tardara cada programa en soltarlo, problema al elegirlos secuencialmente

    public Puertos(){
        Arrays.fill(puertos, false);
        r = new Random();
    }

    public synchronized int getPort(){
        int i = r.nextInt(M); //Elegimos puerto al azar del 0 al M - 1
        while(puertos[i]){ //Si ese esta ocupado seguimos esperando a que alguno se libere
            i = r.nextInt(M);
        }
        puertos[i] = true; //Si esta libre lo marcamos como ocupado y lo devolvemos
        return i + OFFSET; //Devolvemos el numero de puerto teniendo en cuenta que comienza en OFFSET
    }

    public synchronized void closePort(int i){
        i -= OFFSET; //Se dara el puerto con el OFFSET incluido, por lo que hay que restarlo
        puertos[i] = false; //Liberamos el puerto devuelto
    }

}
