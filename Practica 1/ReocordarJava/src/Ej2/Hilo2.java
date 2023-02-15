package Ej2;
import java.util.Random;
public class Hilo2 extends Thread{
    private Entero e;
    private boolean inc;
    private int N;

    public Hilo2(Entero e, boolean inc, int N){
       this.e = e;
       this.inc = inc;
       this.N = N;
    }

    public void run(){
        if (inc) {
            for(int i = 0; i < N; ++i){
                e.incrementar();
            }
        }
        else{
            for(int i = 0; i < N; ++i){
                e.decrementar();
            }
        }
    }
}
