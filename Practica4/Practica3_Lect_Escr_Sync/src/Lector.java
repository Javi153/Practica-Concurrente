import java.util.Random;

public class Lector extends Thread{
    private Storage st;
    private int N;
    private Random rand;

    public Lector(Storage st, int N){
        this.st = st;
        this.N = N;
        rand = new Random();
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            st.requestRead();
            int aux = st.leer(rand.nextInt(0,st.getCap()));
            st.releaseRead();
            System.out.println("Soy el lector " + this.getId() + " y he leido el objeto " + aux);
        }
    }
}
