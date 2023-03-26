public class Consumidor extends Thread{
    private Storage st;
    private int N;

    public Consumidor(Storage st, int N){
        this.st = st;
        this.N = N;
    }

    public void run() {
        for(int i = 0; i < N; ++i){
            int aux = 0;
            try {
                aux = st.extraer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Soy el consumidor " + this.getId() + " y he consumido el objeto " + aux);
        }
    }
}
