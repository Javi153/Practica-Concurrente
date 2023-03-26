import java.util.Random;

public class Escritor extends Thread{
    private Random rand;
    private Storage st;
    private int N;

    public Escritor(Storage st, int N){
        this.st = st;
        this.N = N;
        rand = new Random();
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            int aux = rand.nextInt(0,150);
            st.escribir(aux, rand.nextInt(0, st.getCap()));
            System.out.println("Soy el escritor " + this.getId() + " y he escrito el elemento " + aux);
        }
    }

}
