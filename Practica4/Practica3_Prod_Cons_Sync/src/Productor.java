import java.util.Random;

public class Productor extends Thread{
    private Random r;
    private Storage st;
    private int N;

    public Productor(Storage st, int N){
        this.st = st;
        this.N = N;
        r = new Random();
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            int aux = r.nextInt(0, 15);
            try {
                st.almacenar(aux);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Soy el productor " + this.getId() + " y he producido el elemento " + aux);
        }
    }

}
