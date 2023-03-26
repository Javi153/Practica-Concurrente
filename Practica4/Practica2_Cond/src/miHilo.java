public class miHilo extends Thread{
    private int id;
    private miMonitorCond mon;
    private boolean inc;
    private int N;

    public miHilo(int id, miMonitorCond mon, boolean inc, int N){
        this.id = id;
        this.mon = mon;
        this.inc = inc;
        this.N = N;
    }

    public void run(){
        for(int i = 0; i < N; ++i) {
            mon.op(inc);
            if(inc) {
                System.out.println("Soy el hilo "+id+". Incremento el valor y ahora vale: "+mon.get());
            }
            else{
                System.out.println("Soy el hilo "+id+". Decremento el valor y ahora vale: "+mon.get());
            }
        }
    }
}
